package lise.uebungsprojekt.gamePlatform.game.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<RatingEntity, Int>
