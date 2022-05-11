package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.*
import lise.uebungsprojekt.gamePlatform.game.repository.developer.DeveloperRepository
import lise.uebungsprojekt.gamePlatform.game.repository.platform.*
import lise.uebungsprojekt.gamePlatform.game.repository.rating.RatingRepository
import lise.uebungsprojekt.gamePlatform.game.service.platform.toEntity
import org.springframework.stereotype.Service

interface GameService {
    fun findById(id: Long): Game
    fun findAll(): List<Game>
    fun save(game: Game): Game
}

@Service
class GameServiceImpl(
    private val gameRepo: GameRepository,
    private val ratingRepo: RatingRepository,
    private val developerRepo: DeveloperRepository,
    private val platformRepo: PlatformRepository
) : GameService {
    private fun calcRatingAverage(id: Long): Double {
        var rating = ratingRepo.findAll().filter { it.game.id == id }.map { it.rating.numStar }.average()
        return if (rating.isNaN()) .0 else rating
    }

    override fun findById(id: Long): Game {
        if (gameRepo.existsById(id)) {
            return gameRepo.findById(id).map { it.toDomain(calcRatingAverage(id)) }.get()
        } else {
            throw IllegalArgumentException("Game not found with id: $id")
        }
    }

    override fun findAll(): List<Game> = gameRepo.findAll().map { it.toDomain(calcRatingAverage(it.id!!)) }

    override fun save(game: Game): Game {
        // save new developers and platforms beforehand to get ids
        // prevents saving multiple records of the same developers and platforms
        game.developers.filter { !developerRepo.existsByDeveloper(it.developer) }
            .map { developerRepo.save(it.toEntity() ) }

        game.platform.filter { !platformRepo.existsByPlatform(it.platform) }
            .map { platformRepo.save(it.toEntity()) }

        val gameDeveloperEntities = game.developers.map { developerRepo.findByDeveloper(it.developer) }
            .toMutableSet()
        val gamePlatformEntities = game.platform.map { platformRepo.findByPlatform(it.platform) }
            .toMutableSet()

        val gameEntity: GameEntity = if (gameRepo.existsByTitle(game.title)) {
            gameRepo.findByTitle(game.title)
        } else {
            game.toEntity()
        }

        gameEntity.developer.removeIf { it.id == null }
        gameEntity.platform.removeIf { it.id == null }

        gameEntity.developer.addAll(gameDeveloperEntities)
        gameEntity.platform.addAll(gamePlatformEntities)

        return gameRepo.save(gameEntity).toDomain()
    }
}