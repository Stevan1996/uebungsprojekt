package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class GameController {
    @Autowired
    lateinit var service: GameService

    @GetMapping("/game/{id}")
    fun getGame(@PathVariable id: Int) = service.findById(id)

    @GetMapping("/game")
    fun getAllGames() = service.findAll()
}