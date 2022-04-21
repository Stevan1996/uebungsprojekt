package lise.uebungsprojekt.gamePlatform.game.controller.game

import lise.uebungsprojekt.gamePlatform.game.service.game.Developer
import lise.uebungsprojekt.gamePlatform.game.service.game.Game
import java.time.LocalDate

data class GameDTO(
    val id: Long?,
    val title: String,
    val releaseDate: LocalDate,
    val developers: List<String>,
    val avgRating: Double
)

fun GameDTO.toDomain(): Game = Game(
    null,
    title,
    releaseDate,
    developers.map { Developer(null, it) },
    avgRating
)