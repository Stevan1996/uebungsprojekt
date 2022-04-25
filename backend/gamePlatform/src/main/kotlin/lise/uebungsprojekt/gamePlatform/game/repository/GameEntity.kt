package lise.uebungsprojekt.gamePlatform.game.repository

import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity
import lise.uebungsprojekt.gamePlatform.game.repository.platform.toDomain
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
    val description: String = "",
    val trailer: String = "",
    @ManyToMany(mappedBy = "developedGames")
    val developer: List<DeveloperEntity> = mutableListOf(),
    @ManyToMany(mappedBy = "games")
    val platform: List<PlatformEntity> = mutableListOf()
)

fun GameEntity.toDomain(ratingScore: Double = .0): Game = Game(
    id,
    title,
    releaseDate,
    developer.map { it.toDomain() },
    description,
    trailer,
    ratingScore,
    platform.map {it.toDomain()}
)