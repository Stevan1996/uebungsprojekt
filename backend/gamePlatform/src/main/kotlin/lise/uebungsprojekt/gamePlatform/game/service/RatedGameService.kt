package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.GameRepository
import lise.uebungsprojekt.gamePlatform.game.repository.RatingRepository
import lise.uebungsprojekt.gamePlatform.game.repository.toDomain
import org.springframework.stereotype.Service

interface RatedGameService {
    fun findById(id: Long) : Game
    fun findAll() : List<Game>
}

@Service
class RatedGameServiceImpl(private val gameRepo: GameRepository,
                           private val ratingRepo: RatingRepository) : RatedGameService {
    private fun calcRatingAverage(id: Long) : Double =
        ratingRepo.findAll().filter {it.game.id == id}.map { it.rating.numStar }.average()

    override fun findById(id: Long) : Game {
        if (gameRepo.existsById(id)) {
            return gameRepo.findById(id).map {it.toDomain(calcRatingAverage(id))}.get()
        } else {
            throw IllegalArgumentException("Game not found with id: $id")
        }
    }

    override fun findAll() : List<Game> =
        gameRepo.findAll().map { it.toDomain(calcRatingAverage(it.id!!)) }
}