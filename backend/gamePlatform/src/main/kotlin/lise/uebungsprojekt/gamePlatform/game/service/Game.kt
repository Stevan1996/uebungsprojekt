package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.controller.GameDTO
import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import java.time.LocalDate

data class Game (val id: Long?, val title: String, val releaseDate: LocalDate, val developer: String)

fun Game.toEntity(): GameEntity = GameEntity(
    null,
    title,
    releaseDate,
    developer
)

fun Game.toDTO(): GameDTO = GameDTO(
    id,
    title,
    releaseDate,
    developer
)