package lise.uebungsprojekt.gamePlatform.game.repository.platform

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlatformRepository : JpaRepository<PlatformEntity, Int>