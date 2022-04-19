package lise.uebungsprojekt.gamePlatform.game.service

import lise.uebungsprojekt.gamePlatform.game.controller.RatingDTO
import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.GameRating
import lise.uebungsprojekt.gamePlatform.game.repository.RatingEntity

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