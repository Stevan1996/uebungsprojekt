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
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "game_developer",
        joinColumns = [JoinColumn(name = "game_id")],
        inverseJoinColumns = [JoinColumn(name = "developer_id")]
    )
    var developer: MutableSet<DeveloperEntity> = mutableSetOf(),
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "game_platform",
        joinColumns = [JoinColumn(name = "game_id")],
        inverseJoinColumns = [JoinColumn(name = "platform_id")]
    )
    var platform: MutableSet<PlatformEntity> = mutableSetOf()
)

fun GameEntity.toDomain(ratingScore: Double = .0): Game = Game(
    id,
    title,
    releaseDate,
    developer.map { it.toDomain() }.toMutableSet(),
    description,
    trailer,
    ratingScore,
    platform.map {it.toDomain()}.toMutableSet()
)