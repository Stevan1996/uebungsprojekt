package lise.uebungsprojekt.gamePlatform.game.service.game

import lise.uebungsprojekt.gamePlatform.game.repository.game.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.game.GameRepository
import lise.uebungsprojekt.gamePlatform.game.repository.game.toDomain
import org.springframework.stereotype.Service

interface GameService {
    fun findAll(): List<Game>
    fun findById(id: Long): Game
    fun save(game: GameEntity): GameEntity
}

@Service
class GameServiceImpl(private val repo: GameRepository): GameService {
    override fun findAll() = repo.findAll().map {it.toDomain()}
    override fun findById(id: Long): Game {
        if (repo.existsById(id)) {
            return repo.findById(id).map { it.toDomain() }.get()
        } else {
            throw IllegalArgumentException("Game not found with id: $id")
        }
    }
    override fun save(game: GameEntity): GameEntity = repo.save(game)
}