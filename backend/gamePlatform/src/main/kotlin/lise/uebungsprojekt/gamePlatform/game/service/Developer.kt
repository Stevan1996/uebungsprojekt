package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.DeveloperEntity

data class Developer (val id: Int?, val developer: String)

fun Developer.toEntity(): DeveloperEntity = DeveloperEntity(
    null,
    developer
)