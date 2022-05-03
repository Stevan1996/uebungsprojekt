package lise.uebungsprojekt.gamePlatform.login.repository

import lise.uebungsprojekt.gamePlatform.login.service.User

data class UserEntity (
    val id: Long? = null,
    val email: String = "",
    val password: String = ""
)

fun UserEntity.toDomain(): User = User(id, email, password)

