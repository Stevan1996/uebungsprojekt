package lise.uebungsprojekt.gamePlatform.game.controller.platform

import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform

data class PlatformDTO(val id: Int?, val platform: String)

fun PlatformDTO.toDomain(): Platform = Platform(id, platform)