package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.entity.LoginData
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class LoginController {
    @PostMapping("/login")
    fun checkLogin(@RequestBody loginData: LoginData) : Boolean {
        return Random.nextDouble() > 0.5
    }
}