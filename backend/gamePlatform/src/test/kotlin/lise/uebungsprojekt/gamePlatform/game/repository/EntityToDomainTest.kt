package lise.uebungsprojekt.gamePlatform.game.repository

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import lise.uebungsprojekt.gamePlatform.game.repository.developer.DeveloperEntity
import lise.uebungsprojekt.gamePlatform.game.repository.developer.toDomain
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity
import lise.uebungsprojekt.gamePlatform.game.repository.platform.toDomain
import lise.uebungsprojekt.gamePlatform.game.service.Developer
import lise.uebungsprojekt.gamePlatform.game.service.Game
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class EntityToDomainTest {
    @Nested
    inner class GameEntityTest{
        @Test
        fun `game entity to domain conversion`() {
            val gameEntity = GameEntity(
                42,
                "Game Title",
                LocalDate.of(2000, 1, 1),
                "Game Description",
                "https://youtube.com"
            )

            val gameDomain = Game(
                42,
                "Game Title",
                LocalDate.of(2000, 1, 1),
                mutableSetOf<Developer>(),
                "Game Description",
                "https://youtube.com",
                .0,
                mutableSetOf<Platform>()
            )

            gameEntity.toDomain() shouldBe gameDomain
        }
    }

    @Nested
    inner class PlatformEntityTest {
        @Test
        fun `platform entity to domain conversion`() {
            val platformEntity = PlatformEntity(42, "Nintendo Switch")
            val platformDomain = Platform(42, "Nintendo Switch")

            platformEntity.toDomain() shouldBe platformDomain
        }

        @Test
        fun `entity conversion should not produce different id`() {
            val platformEntity = PlatformEntity(null, "Nintendo Switch")
            val platformDomain = Platform(0, "Nintendo Switch")

            platformEntity.toDomain() shouldNotBe platformDomain
        }

        @Test
        fun `entity conversion should not produce different platform name`() {
            val platformEntity = PlatformEntity(0, "PS5")
            val platformDomain = Platform(0, "XBOX")

            platformEntity.toDomain() shouldNotBe platformDomain
        }

        @Test
        fun `game list should not affect entity conversion`() {
            val platformEntity = PlatformEntity(0, "PS4", mutableSetOf(GameEntity()))
            val platformDomain = Platform(0, "PS4")

            platformEntity.toDomain() shouldBe platformDomain
        }
    }

    @Nested
    inner class DeveloperEntityTest {
        @Test
        fun `developer entity to domain conversion`() {
            val developerEntity = DeveloperEntity(42, "Game Freak")
            val developerDomain = Developer(42, "Game Freak")

            developerEntity.toDomain() shouldBe developerDomain
        }

        @Test
        fun `game list should not affect entity conversion`() {
            val developerEntity = DeveloperEntity(42, "Game Freak", mutableSetOf(GameEntity()))
            val developerDomain = Developer(42, "Game Freak")

            developerEntity.toDomain() shouldBe developerDomain
        }

        @Test
        fun `entity conversion should not produce different id`() {
            val developerEntity = DeveloperEntity(null, "Game Freak")
            val developerDomain = Developer(0, "Game Freak")

            developerEntity.toDomain() shouldNotBe developerDomain
        }

        @Test
        fun `entity conversion should not produce different developer name`() {
            val developerEntity = DeveloperEntity(42, "Game Freak")
            val developerDomain = Developer(42, "Intelligent Systems")

            developerEntity.toDomain() shouldNotBe developerDomain
        }
    }
}