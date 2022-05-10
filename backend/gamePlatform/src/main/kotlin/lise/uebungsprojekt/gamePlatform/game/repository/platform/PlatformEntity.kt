package lise.uebungsprojekt.gamePlatform.game.repository.platform

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "platforms")
data class PlatformEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @NaturalId
    val platform: String = ""
) {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    var games: MutableSet<GameEntity> = mutableSetOf()

    constructor(id: Int? = null,
                platform: String = "",
                games: MutableSet<GameEntity> = mutableSetOf()):
            this(id, platform) {
                this.games = games
            }
}

fun PlatformEntity.toDomain(): Platform = Platform(id, platform)