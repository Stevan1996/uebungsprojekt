package lise.uebungsprojekt.gamePlatform.login.controller

import lise.uebungsprojekt.gamePlatform.login.service.User

data class UserDTO (
    val id: Long?,
    val email: String,
    val password: String
)

fun UserDTO.toDomain(): User = User(id, email, password)