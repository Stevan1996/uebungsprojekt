package lise.uebungsprojekt.gamePlatform.game.repository.developer

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeveloperRepository : JpaRepository<DeveloperEntity, Int> {
    fun existsByDeveloper(developerName: String): Boolean
    fun findByDeveloper(developerName: String): DeveloperEntity
}