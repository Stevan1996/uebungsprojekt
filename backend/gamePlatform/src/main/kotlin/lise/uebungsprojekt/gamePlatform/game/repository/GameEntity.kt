package lise.uebungsprojekt.gamePlatform.game.repository

import lise.uebungsprojekt.gamePlatform.game.service.Game
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "games")
data class GameEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String = "",
    val releaseDate: LocalDate = LocalDate.now(),
    val developer: String = ""
)

fun GameEntity.toDomain(): Game = Game(
    id,
    title,
    releaseDate,
    developer
)