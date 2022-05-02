package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.DeveloperEntity

data class Developer (val id: Int? = null, val developer: String)

fun Developer.toEntity(): DeveloperEntity = DeveloperEntity(
    id,
    developer
)

fun Developer.toDTO(): String = developer