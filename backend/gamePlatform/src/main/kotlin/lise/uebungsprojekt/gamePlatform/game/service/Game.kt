package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.controller.GameDTO
import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import lise.uebungsprojekt.gamePlatform.game.service.platform.toDTO
import lise.uebungsprojekt.gamePlatform.game.service.platform.toEntity
import java.time.LocalDate

data class Game (
    val id: Long?,
    val title: String,
    val releaseDate: LocalDate,
    val developers: MutableSet<Developer>,
    val description: String,
    val trailer: String,
    val avgRating: Double,
    val platform: MutableSet<Platform>
)

fun Game.toEntity(): GameEntity = GameEntity(
    id,
    title,
    releaseDate,
    description,
    trailer,
    developers.map {it.toEntity()}.toMutableSet(),
    platform.map {it.toEntity()}.toMutableSet()
)

fun Game.toDTO(): GameDTO = GameDTO(
    id,
    title,
    releaseDate,
    developers.map { it.toDTO() }.toSet(),
    description,
    trailer,
    avgRating,
    platform.map { it.toDTO() }.toSet()
)