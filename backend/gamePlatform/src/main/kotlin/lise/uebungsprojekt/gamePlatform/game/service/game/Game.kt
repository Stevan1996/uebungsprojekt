package lise.uebungsprojekt.gamePlatform.game.service.game

import lise.uebungsprojekt.gamePlatform.game.controller.game.GameDTO
import lise.uebungsprojekt.gamePlatform.game.repository.game.GameEntity
import java.time.LocalDate

data class Game (
    val id: Long?,
    val title: String,
    val releaseDate: LocalDate,
    val developers: List<Developer>,
    val avgRating: Double
)

fun Game.toEntity(): GameEntity = GameEntity(
    null,
    title,
    releaseDate,
    developers.map {it.toEntity()}
)

fun Game.toDTO(): GameDTO = GameDTO(
    id,
    title,
    releaseDate,
    developers.map { it.developer },
    avgRating
)