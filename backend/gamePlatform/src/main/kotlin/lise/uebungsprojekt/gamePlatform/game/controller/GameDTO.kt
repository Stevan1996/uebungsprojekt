package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.service.Developer
import lise.uebungsprojekt.gamePlatform.game.service.Game
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import java.time.LocalDate

data class GameDTO(
    val id: Long? = null,
    val title: String,
    val releaseDate: LocalDate,
    val developers: Set<String>,
    val description: String,
    val trailer: String,
    val avgRating: Double,
    val platform: Set<String>
)

fun GameDTO.toDomain(): Game = Game(
    id,
    title,
    releaseDate,
    developers.map { Developer(null, it) }.toMutableSet(),
    description,
    trailer,
    avgRating,
    platform.map { Platform(null, it) }.toMutableSet()
)