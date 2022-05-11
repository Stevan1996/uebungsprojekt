package lise.uebungsprojekt.gamePlatform.game.repository.developer

import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.service.Developer
import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "developers")
data class DeveloperEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @NaturalId
    val developer: String = ""
) {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "developer")
    var developedGames: MutableSet<GameEntity> = mutableSetOf()

    constructor(id: Int? = null,
                developer: String = "",
                developedGames: MutableSet<GameEntity> = mutableSetOf()):
            this(id, developer) {
                this.developedGames = developedGames
            }
}

fun DeveloperEntity.toDomain(): Developer = Developer(id, developer)