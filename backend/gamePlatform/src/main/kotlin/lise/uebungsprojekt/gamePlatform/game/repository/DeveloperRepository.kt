package lise.uebungsprojekt.gamePlatform.game.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeveloperRepository : JpaRepository<DeveloperEntity, Int>

fun DeveloperRepository.existsByName(developerName: String): Boolean =
    findAll().any { it.developer == developerName }

fun DeveloperRepository.getDeveloperByName(developer: String): DeveloperEntity {
    val result = findAll().filter { it.developer == developer }
    if (result.isEmpty()) {
        throw IllegalArgumentException("$developer does not exists in database")
    }

    return result[0]
}