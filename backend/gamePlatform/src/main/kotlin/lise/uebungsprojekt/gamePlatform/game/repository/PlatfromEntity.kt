package lise.uebungsprojekt.gamePlatform.game.repository

import javax.persistence.*

enum class Platform {
    NONE,
    PC,
    PS5,
    XBOX,
    NINTENDO_SWITCH
}

@Entity
@Table(name = "platforms")
data class PlatfromEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val platform: Platform = Platform.NONE
    )