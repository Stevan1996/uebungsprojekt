package lise.uebungsprojekt.gamePlatform.game.controller

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.GameRating
import lise.uebungsprojekt.gamePlatform.game.service.Rating

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