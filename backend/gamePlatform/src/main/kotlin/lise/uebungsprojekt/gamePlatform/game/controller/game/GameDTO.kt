package lise.uebungsprojekt.gamePlatform.game.controller.game

import lise.uebungsprojekt.gamePlatform.game.service.game.Developer
import lise.uebungsprojekt.gamePlatform.game.service.game.Game
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import java.time.LocalDate

data class GameDTO(
    val id: Long?,
    val title: String,
    val releaseDate: LocalDate,
    val developers: List<String>,
    val avgRating: Double,
    val platform: List<String>
)

fun GameDTO.toDomain(): Game = Game(
    null,
    title,
    releaseDate,
    developers.map { Developer(null, it) },
    avgRating,
    platform.map { Platform(null, it) }
)