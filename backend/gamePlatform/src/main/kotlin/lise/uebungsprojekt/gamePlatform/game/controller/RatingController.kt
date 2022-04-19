package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.service.RatingService
import lise.uebungsprojekt.gamePlatform.game.service.toDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RatingController(private val service: RatingService) {

    @GetMapping("/game/{id}/rating")
    fun getAllRatings(@PathVariable id: Long): List<RatingDTO> =
        service.findByGameId(id).map { it.toDTO() }

    @GetMapping("/game/all/rating")
    fun getAllRating(): List<RatingDTO> = service.findAll().map { it.toDTO() }
}