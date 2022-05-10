package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.repository.DeveloperEntity

data class Developer (val id: Int? = null, val developer: String) {
    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is Developer) {
            false
        } else {
            other.developer == developer
        }
    }
}

fun Developer.toEntity(): DeveloperEntity = DeveloperEntity(
    id,
    developer
)

fun Developer.toDTO(): String = developer