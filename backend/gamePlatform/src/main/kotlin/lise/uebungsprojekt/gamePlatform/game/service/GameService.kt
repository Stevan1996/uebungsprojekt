package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.entity.Game
import lise.uebungsprojekt.gamePlatform.game.repository.GameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface GameService {
    fun findAll(): List<Game>
    fun findById(id: Int): Game?
}

@Service
class GameServiceImpl: GameService {
    @Autowired
    lateinit var repo: GameRepository

    override fun findAll() = repo.findAll()
    override fun findById(id: Int) = repo.findById(id)
}