package lise.uebungsprojekt.gamePlatform.game.service.game

import lise.uebungsprojekt.gamePlatform.game.repository.game.DeveloperEntity

data class Developer (val id: Int?, val developer: String)

fun Developer.toEntity(): DeveloperEntity = DeveloperEntity(
    null,
    developer
)