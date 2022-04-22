package lise.uebungsprojekt.gamePlatform.game.repository

import lise.uebungsprojekt.gamePlatform.game.service.Developer
import javax.persistence.*

@Entity
@Table(name = "developers")
data class DeveloperEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val developer: String = "",
    @ManyToMany
    @JoinTable(
        name = "game_developer",
        joinColumns = [JoinColumn(name = "developer_id")],
        inverseJoinColumns = [JoinColumn(name = "game_id")]
    )
    val developedGames: List<GameEntity> = mutableListOf()
)

fun DeveloperEntity.toDomain(): Developer = Developer(id, developer)