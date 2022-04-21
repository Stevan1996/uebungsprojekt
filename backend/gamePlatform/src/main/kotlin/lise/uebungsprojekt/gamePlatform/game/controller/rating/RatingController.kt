package lise.uebungsprojekt.gamePlatform.game.controller.rating

import lise.uebungsprojekt.gamePlatform.game.service.rating.RatingService
import lise.uebungsprojekt.gamePlatform.game.service.rating.toDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RatingController(private val service: RatingService) {

    @GetMapping("/game/{id}/rating")
    fun getRatingsByGameId(@PathVariable id: Long): List<RatingDTO> =
        service.findByGameId(id).map { it.toDTO() }

    @GetMapping("/game/ratings")
    fun getAllRatings(): List<RatingDTO> = service.findAll().map { it.toDTO() }
}