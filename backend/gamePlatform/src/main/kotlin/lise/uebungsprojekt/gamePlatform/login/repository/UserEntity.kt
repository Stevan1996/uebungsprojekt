package lise.uebungsprojekt.gamePlatform.login.repository

import lise.uebungsprojekt.gamePlatform.login.service.User
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String = "",
    val password: String = ""
)

fun UserEntity.toDomain(): User = User(id, email, password)

