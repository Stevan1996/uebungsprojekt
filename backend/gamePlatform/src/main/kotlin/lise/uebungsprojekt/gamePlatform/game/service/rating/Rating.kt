package lise.uebungsprojekt.gamePlatform.game.service.rating

import lise.uebungsprojekt.gamePlatform.game.controller.rating.RatingDTO
import lise.uebungsprojekt.gamePlatform.game.repository.game.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.rating.GameRating
import lise.uebungsprojekt.gamePlatform.game.repository.rating.RatingEntity

data class Rating(
    val id: Int?,
    val rating: GameRating,
    val comment: String,
    val game: GameEntity
)

fun Rating.toEntity(): RatingEntity = RatingEntity(
    id,
    rating,
    comment,
    game
)

fun Rating.toDTO(): RatingDTO = RatingDTO(
    id,
    rating,
    comment,
    game
)