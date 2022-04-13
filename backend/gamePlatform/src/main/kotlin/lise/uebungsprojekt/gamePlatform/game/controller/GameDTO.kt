package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.service.Game
import java.time.LocalDate

data class GameDTO(val id: Long?, val title: String, val releaseDate: LocalDate,  val developer: String)

fun GameDTO.toDomain(): Game = Game(
    null,
    title,
    releaseDate,
    developer
)