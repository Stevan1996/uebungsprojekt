package lise.uebungsprojekt.gamePlatform.game.repository.platform

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlatformRepository : JpaRepository<PlatformEntity, Int>

fun PlatformRepository.existsByName(platformName: String): Boolean =
    findAll().any {it.platform == platformName}

fun PlatformRepository.getPlatformByName(platform: String): PlatformEntity {
    val result = findAll().filter { it.platform == platform }
    if (result.isEmpty()) {
        throw IllegalArgumentException("$platform does not exist in database")
    }

    return result[0]
}