package lise.uebungsprojekt.gamePlatform.game.controller.game

import lise.uebungsprojekt.gamePlatform.game.repository.game.toDomain
import lise.uebungsprojekt.gamePlatform.game.service.game.GameService
import lise.uebungsprojekt.gamePlatform.game.service.game.RatedGameService
import lise.uebungsprojekt.gamePlatform.game.service.game.toDTO
import lise.uebungsprojekt.gamePlatform.game.service.game.toEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class GameController(private val gameService: GameService, private val ratedGameService: RatedGameService) {

    @GetMapping("/game/{id}")
    fun getGame(@PathVariable id: Long): GameDTO =
        ratedGameService.findById(id).toDTO()

    @GetMapping("/game")
    fun getAllGames(): List<GameDTO> =
        ratedGameService.findAll().map {it.toDTO()}

    @PostMapping("/game")
    fun saveGame(@RequestBody game: GameDTO): GameDTO {
        return gameService.save(game.toDomain().toEntity()).toDomain().toDTO()
    }
}