package lise.uebungsprojekt.gamePlatform.game.repository.platform

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import javax.persistence.*

@Entity
@Table(name = "platforms")
data class PlatformEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val platform: String = "",
    @ManyToMany
    @JoinTable(
        name = "game_platform",
        joinColumns = [JoinColumn(name = "platform_id")],
        inverseJoinColumns = [JoinColumn(name = "game_id")]
    )
    val games: List<GameEntity> = mutableListOf()
)

fun PlatformEntity.toDomain(): Platform = Platform(id, platform)