package lise.uebungsprojekt.gamePlatform.game.service.platform

import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformRepository
import lise.uebungsprojekt.gamePlatform.game.repository.platform.toDomain
import org.springframework.stereotype.Service

interface PlatformService {
    fun findAll(): List<Platform>
}

@Service
class PlatformServiceImpl(private val repo: PlatformRepository) : PlatformService {
    override fun findAll() = repo.findAll().map {it.toDomain()}
}