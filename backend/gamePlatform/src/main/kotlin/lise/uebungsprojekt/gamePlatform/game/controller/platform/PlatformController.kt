package lise.uebungsprojekt.gamePlatform.game.controller.platform

import lise.uebungsprojekt.gamePlatform.game.service.platform.PlatformService
import lise.uebungsprojekt.gamePlatform.game.service.platform.toDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlatformController(private val service: PlatformService) {

    @GetMapping("/game/platforms")
    fun getAllPlatforms(): List<String>{
        println("received")
        return service.findAll().map { it.toDTO() }
    }
}