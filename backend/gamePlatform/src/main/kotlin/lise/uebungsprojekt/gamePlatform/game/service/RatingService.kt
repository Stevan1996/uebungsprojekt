package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.RatingRepository
import lise.uebungsprojekt.gamePlatform.game.repository.toDomain
import lise.uebungsprojekt.gamePlatform.game.repository.toRating
import org.springframework.stereotype.Service

interface RatingService{
    fun findByGameId(gameId: Long): List<Rating>
    fun findAll(): List<Rating>
}

@Service
class RatingServiceImpl(private val repo: RatingRepository) : RatingService {
    override fun findByGameId(gameId: Long): List<Rating> =
        repo.findAll().filter { it.game.id == gameId }.map { it.toRating() }

    override fun findAll(): List<Rating> = repo.findAll().map { it.toRating() }
}