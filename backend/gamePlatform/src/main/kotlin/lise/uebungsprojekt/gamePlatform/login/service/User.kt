package lise.uebungsprojekt.gamePlatform.login.service

import lise.uebungsprojekt.gamePlatform.login.controller.UserDTO
import lise.uebungsprojekt.gamePlatform.login.repository.UserEntity
import org.mindrot.jbcrypt.BCrypt

data class User(
    val id: Long?,
    val email: String,
    val password: String
)

fun User.toEntity(): UserEntity {
    val hashedPW = BCrypt.hashpw(password, BCrypt.gensalt())
    return UserEntity(id, email, hashedPW)
}

fun User.toDTO(): UserDTO = UserDTO(id, email, password)