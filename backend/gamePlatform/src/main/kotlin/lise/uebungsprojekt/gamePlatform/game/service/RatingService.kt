package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.RatingRepository
import lise.uebungsprojekt.gamePlatform.game.repository.toDomain
import lise.uebungsprojekt.gamePlatform.game.repository.toRating
import org.springframework.stereotype.Service

interface RatingService{
    fun findByGameId(gameId: Long): List<Rating>
    fun calcRatingAverage(gameId: Long): Double
    fun findAll(): List<Rating>
    fun calcAllRatingAverages(): Map<Game, Double>
}

@Service
class RatingServiceImpl(private val repo: RatingRepository) : RatingService {
    override fun findByGameId(gameId: Long): List<Rating> =
        repo.findAll().filter { it.game.id == gameId }.map { it.toRating() }

    override fun calcRatingAverage(gameId: Long): Double =
        findByGameId(gameId).map { it.rating.numStar }.average()

    override fun findAll(): List<Rating> = repo.findAll().map { it.toRating() }

    override fun calcAllRatingAverages(): Map<Game, Double> {
        val rating = findAll()
        return rating.groupBy(keySelector = { it.game }, valueTransform = {it.rating.numStar})
            .mapValues { it.value.average()}.mapKeys { it.key.toDomain() }
    }
}