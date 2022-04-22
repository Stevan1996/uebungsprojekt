package lise.uebungsprojekt.gamePlatform.game.repository.rating

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.service.rating.Rating
import javax.persistence.*

enum class GameRating(val numStar: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5)
}

@Entity
@Table(name = "ratings")
data class RatingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val rating: GameRating = GameRating.ZERO,
    val comment: String = "",
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    val game: GameEntity = GameEntity()
)

fun RatingEntity.toRating(): Rating = Rating(
    id,
    rating,
    comment,
    game
)