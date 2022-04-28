package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.GameRepository
import lise.uebungsprojekt.gamePlatform.game.repository.rating.RatingRepository
import lise.uebungsprojekt.gamePlatform.game.repository.toDomain
import org.springframework.stereotype.Service

interface GameService {
    fun findById(id: Long) : Game
    fun findAll() : List<Game>
    fun save(game: GameEntity) : GameEntity
}

@Service
class GameServiceImpl(private val gameRepo: GameRepository,
                           private val ratingRepo: RatingRepository
) : GameService {
    private fun calcRatingAverage(id: Long) : Double {
        var rating = ratingRepo.findAll().filter { it.game.id == id }.map { it.rating.numStar }.average()
        return if (rating.isNaN()) .0 else rating
    }

    override fun findById(id: Long) : Game {
        if (gameRepo.existsById(id)) {
            return gameRepo.findById(id).map {it.toDomain(calcRatingAverage(id))}.get()
        } else {
            throw IllegalArgumentException("Game not found with id: $id")
        }
    }

    override fun findAll() : List<Game> =
        gameRepo.findAll().map { it.toDomain(calcRatingAverage(it.id!!)) }

    override fun save(game: GameEntity): GameEntity = gameRepo.save(game)
}