package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.repository.toDomain
import lise.uebungsprojekt.gamePlatform.game.service.GameService
import lise.uebungsprojekt.gamePlatform.game.service.toDTO
import lise.uebungsprojekt.gamePlatform.game.service.toEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class GameController(private val service: GameService) {

    @GetMapping("/game/{id}")
    fun getGame(@PathVariable id: Long): GameDTO = service.findById(id).toDTO()

    @GetMapping("/game")
    fun getAllGames(): List<GameDTO> = service.findAll().map {it.toDTO()}

    @PostMapping("/game")
    fun saveGame(@RequestBody game: GameDTO): GameDTO {
        return service.save(game.toDomain().toEntity()).toDomain().toDTO()
    }
}