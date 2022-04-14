package lise.uebungsprojekt.gamePlatform.game.repository

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

enum class GameRating(val numStar: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5)
}

@Entity
@Table(name = "ratings")
data class Rating(@Id val id: Int, val rating: GameRating, val comment: String, val game_id : Int)