package lise.uebungsprojekt.gamePlatform.game.service.platform

import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity

data class Platform(val id: Int?, val platform: String) {
    override fun equals(other: Any?): Boolean {
        return if (other == null || other !is Platform) {
            false
        } else {
            other.platform == platform
        }
    }
}

fun Platform.toEntity(): PlatformEntity = PlatformEntity(id, platform)

fun Platform.toDTO(): String = platform