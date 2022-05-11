package lise.uebungsprojekt.gamePlatform.login.controller

import lise.uebungsprojekt.gamePlatform.login.repository.toDomain
import lise.uebungsprojekt.gamePlatform.login.service.UserService
import lise.uebungsprojekt.gamePlatform.login.service.toDTO
import lise.uebungsprojekt.gamePlatform.login.service.toEntity
import org.mindrot.jbcrypt.BCrypt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class LoginController(private val userService: UserService) {
    @PostMapping("/login")
    fun login(@RequestBody user: UserDTO): Boolean {
        val userPW = userService.getPasswordByEmail(user.email)

        return BCrypt.checkpw(user.password, userPW)
    }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): UserDTO = userService.findById(id).toDTO()

    @PostMapping("/user")
    fun registerUser(@RequestBody user: UserDTO): UserDTO =
        userService.saveUser(user.toDomain()).toDTO()

    @GetMapping("/user/email/{email}")
    fun checkEmailExistence(@PathVariable email: String): Boolean = userService.emailExists(email)
}