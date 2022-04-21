package lise.uebungsprojekt.gamePlatform.game.service.platform

import lise.uebungsprojekt.gamePlatform.game.controller.platform.PlatformDTO
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity

data class Platform(val id: Int?, val platform: String)

fun Platform.toEntity(): PlatformEntity = PlatformEntity(id, platform)

fun Platform.toDTO(): PlatformDTO = PlatformDTO(id, platform)