package lise.uebungsprojekt.gamePlatform.game.controller.rating

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.rating.GameRating
import lise.uebungsprojekt.gamePlatform.game.service.rating.Rating

data class RatingDTO(
    val id: Int?,
    val rating: GameRating,
    val comment: String,
    val game: GameEntity
)

fun RatingDTO.toDomain(): Rating = Rating(
    null,
    rating,
    comment,
    game
)