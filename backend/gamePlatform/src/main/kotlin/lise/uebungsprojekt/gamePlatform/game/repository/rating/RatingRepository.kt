package lise.uebungsprojekt.gamePlatform.game.repository.rating

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<RatingEntity, Int>
