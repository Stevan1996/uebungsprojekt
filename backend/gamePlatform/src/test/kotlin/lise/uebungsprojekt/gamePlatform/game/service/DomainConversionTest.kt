package lise.uebungsprojekt.gamePlatform.game.service

import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import lise.uebungsprojekt.gamePlatform.game.controller.GameDTO
import lise.uebungsprojekt.gamePlatform.game.repository.DeveloperEntity
import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import lise.uebungsprojekt.gamePlatform.game.service.platform.toDTO
import lise.uebungsprojekt.gamePlatform.game.service.platform.toEntity
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate

internal class DomainConversionTest {
    @Nested
    inner class GameDomainTests{
        @Test
        fun `game domain to DTO conversion`() {
            val gameDomain = Game(
                2,
                "Game Title",
                LocalDate.of(2000, 1, 1),
                mutableSetOf<Developer>(),
                "Game Description",
                "https://youtube.com",
                .0,
                mutableSetOf<Platform>()
            )

            val gameDTO = GameDTO(
                2,
                "Game Title",
                LocalDate.of(2000, 1, 1),
                mutableSetOf<String>(),
                "Game Description",
                "https://youtube.com",
                .0,
                mutableSetOf<String>()
            )

            gameDomain.toDTO() shouldBe gameDTO
        }

        @Test
        fun `game domain to entity conversion`() {
            val gameDomain = Game(
                2,
                "Game Title",
                LocalDate.of(2000, 1, 1),
                mutableSetOf<Developer>(),
                "Game Description",
                "https://youtube.com",
                .0,
                mutableSetOf<Platform>()
            )

            val gameEntity = GameEntity(
                2,
                "Game Title",
                LocalDate.of(2000, 1, 1),
                "Game Description",
                "https://youtube.com",
                mutableSetOf<DeveloperEntity>(),
                mutableSetOf<PlatformEntity>()
            )

            gameDomain.toEntity() shouldBe gameEntity
        }
    }

    @Nested
    inner class DeveloperDomainTests {
        @Test
        fun `developer domain to DTO conversion`() {
            val developerDomain = Developer(Mockito.anyInt(), "Game Studio")

            developerDomain.toDTO() shouldBe "Game Studio"
        }

        @Test
        fun `domain to DTO conversion should not produce different developer name`() {
            val developerDomain = Developer(Mockito.anyInt(), "Game Studio")

            developerDomain.toDTO() shouldNotBe "Different Studio"
        }

        @Test
        fun `developer domain to entity conversion`() {
            val developerDomain = Developer(1, "Game Studio")
            val developerEntity = DeveloperEntity(1, "Game Studio")

            developerDomain.toEntity() shouldBe developerEntity
        }

        @Test
        fun `domain to entity conversion should not produce different developer name`() {
            val developerDomain = Developer(1, "Game Studio")
            val developerEntity = DeveloperEntity(1, "Another Studio")

            developerDomain.toEntity() shouldNotBe developerEntity
        }

        @Test
        fun `domain to entity conversion should produce empty developed games list`() {
            val developerDomain = Developer(1, "Game Studio")

            developerDomain.toEntity().developedGames shouldNotContain Mockito.any(GameEntity::class.java)
        }
    }

    @Nested
    inner class PlatformDomainTests {
        @Test
        fun `platform domain to DTO`() {
            val platformDomain = Platform(Mockito.anyInt(), "PS4")

            platformDomain.toDTO() shouldBe "PS4"
        }

        @Test
        fun `domain to DTO conversion should not produce different name`() {
            val platformDomain = Platform(Mockito.anyInt(), "PS4")

            platformDomain.toDTO() shouldNotBe "PS5"
        }

        @Test
        fun `domain to entity conversion should produce empty game list`() {
            val platformDomain = Platform(Mockito.anyInt(), "PS4")

            platformDomain.toEntity().games shouldNotContain Mockito.any(GameEntity::class.java)
        }

        @Test
        fun `domain to entity conversion`() {
            val platformDomain = Platform(1, "PS4")
            val platformEntity = PlatformEntity(1, "PS4")

            platformDomain.toEntity() shouldBe platformEntity
        }

        @Test
        fun `domain to entity conversion should not produce different name`() {
            val platformDomain = Platform(1, "PS4")
            val platformEntity = PlatformEntity(1, "PS5")

            platformDomain.toEntity() shouldNotBe platformEntity
        }
    }
}