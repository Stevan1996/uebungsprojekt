package lise.uebungsprojekt.gamePlatform.game.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository : JpaRepository<GameEntity, Long> {
    fun existsByTitle(title: String): Boolean
    fun findByTitle(title: String): GameEntity
}