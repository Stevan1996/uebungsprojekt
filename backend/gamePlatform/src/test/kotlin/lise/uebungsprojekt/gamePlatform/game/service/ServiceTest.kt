package lise.uebungsprojekt.gamePlatform.game.service

import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldNotBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import lise.uebungsprojekt.gamePlatform.game.repository.DeveloperEntity
import lise.uebungsprojekt.gamePlatform.game.repository.DeveloperRepository
import lise.uebungsprojekt.gamePlatform.game.repository.GameEntity
import lise.uebungsprojekt.gamePlatform.game.repository.GameRepository
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformRepository
import lise.uebungsprojekt.gamePlatform.game.repository.rating.GameRating
import lise.uebungsprojekt.gamePlatform.game.repository.rating.RatingEntity
import lise.uebungsprojekt.gamePlatform.game.repository.rating.RatingRepository
import lise.uebungsprojekt.gamePlatform.game.service.platform.Platform
import lise.uebungsprojekt.gamePlatform.game.service.platform.PlatformServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ServiceTest {
    private val gameRepo = mockk<GameRepository>()
    private val ratingRepo = mockk<RatingRepository>()
    private val developerRepo = mockk<DeveloperRepository>()
    private val platformRepo = mockk<PlatformRepository>()
    private val gameService = GameServiceImpl(gameRepo, ratingRepo, developerRepo, platformRepo)
    private val platformService = PlatformServiceImpl(platformRepo)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class GameServiceTests {
        @Test
        fun `get all games`() {
            every { gameRepo.findAll() } returns listOf(
                GameEntity(
                    42,
                    "Fire Emblem",
                    LocalDate.of(2042, 4, 2),
                    "RPG",
                    "https://youtube.com",
                    mutableSetOf(DeveloperEntity(4, "Nintendo")),
                    mutableSetOf(PlatformEntity(2, "Switch"))
                )
            )

            val game = listOf(
                Game(
                    42,
                    "Fire Emblem",
                    LocalDate.of(2042, 4, 2),
                    mutableSetOf(Developer(4, "Nintendo")),
                    "RPG",
                    "https://youtube.com",
                    .0,
                    mutableSetOf(Platform(2, "Switch"))
                )
            )

            every { ratingRepo.findAll() } returns listOf()
            val games = gameService.findAll()
            games.isEmpty() shouldNotBe true
            games.count() shouldBe 1
            games shouldBe game
        }

        @Test
        fun `get all games with 2 list items`() {
            val sampleGameEntityFire = GameEntity(
                42,
                "Fire Emblem",
                LocalDate.of(2042, 4, 2),
                "RPG",
                "https://youtube.com",
                mutableSetOf(DeveloperEntity(2, "Game Studio")),
                mutableSetOf(PlatformEntity(4, "Nintendo Switch"))
            )
            val sampleGameEntityPoke = GameEntity(
                1,
                "Pokemon",
                LocalDate.of(1996, 9, 6),
                "Another RPG",
                "pokemon-trailer",
                mutableSetOf(
                    DeveloperEntity(1, "Nintendo"),
                    DeveloperEntity(3, "Game Freak")
                ),
                mutableSetOf(PlatformEntity(4, "Nintendo Switch"))
            )

            every { gameRepo.findAll() } returns listOf(
                sampleGameEntityFire,
                sampleGameEntityPoke
            )
            every { ratingRepo.findAll() } returns listOf(
                RatingEntity(
                    1,
                    GameRating.FOUR,
                    "comment1",
                    sampleGameEntityFire
                ),
                RatingEntity(
                    2,
                    GameRating.THREE,
                    "comment2",
                    sampleGameEntityFire
                ),
                RatingEntity(
                    3,
                    GameRating.TWO,
                    "comment3",
                    sampleGameEntityPoke
                )
            )

            val expectedGames = listOf(
                Game(
                    42,
                    "Fire Emblem",
                    LocalDate.of(2042, 4, 2),
                    mutableSetOf(Developer(2, "Game Studio")),
                    "RPG",
                    "https://youtube.com",
                    3.5,
                    mutableSetOf(Platform(4, "Nintendo Switch"))
                ),
                Game(
                    1,
                    "Pokemon",
                    LocalDate.of(1996, 9, 6),
                    mutableSetOf(
                        Developer(1, "Nintendo"),
                        Developer(3, "Game Freak")
                    ),
                    "Another RPG",
                    "pokemon-trailer",
                    2.0,
                    mutableSetOf(Platform(4, "Nintendo Switch"))
                )
            )

            val games = gameService.findAll()
            games.isEmpty() shouldBe false
            games.count() shouldBe 2
            games shouldBe expectedGames
        }

        @Test
        fun `get all games on empty list`() {
            every { gameRepo.findAll() } returns listOf()
            every { ratingRepo.findAll() } returns listOf()

            gameService.findAll().isEmpty() shouldBe true
        }

        @Test
        fun `get game by existing id`() {
            val sampleGameEntity = GameEntity(
                42,
                "Fire Emblem",
                LocalDate.of(2042, 4, 2),
                "RPG",
                "https://youtube.com",
                mutableSetOf(DeveloperEntity(4, "Nintendo")),
                mutableSetOf(PlatformEntity(2, "Nintendo Switch"))
            )

            val slot = slot<Long>()
            every { gameRepo.findById(capture(slot)) } answers {
                if (slot.captured == 42L) {
                    Optional.of(sampleGameEntity)
                } else {
                    Optional.empty()
                }
            }
            every { gameRepo.existsById(capture(slot)) } answers {
                slot.captured == 42L
            }
            every { ratingRepo.findAll() } returns listOf(
                RatingEntity(
                    1,
                    GameRating.FIVE,
                    "comment",
                    sampleGameEntity
                ),
                RatingEntity(
                    2,
                    GameRating.ONE,
                    "comment2",
                    sampleGameEntity
                )
            )

            val game = Game(
                42,
                "Fire Emblem",
                LocalDate.of(2042, 4, 2),
                mutableSetOf(Developer(4, "Nintendo")),
                "RPG",
                "https://youtube.com",
                3.0,
                mutableSetOf(Platform(2, "Nintendo Switch"))
            )

            gameService.findById(42) shouldBe game
        }

        @Test
        fun `get game by non-existing id`() {
            val slot = slot<Long>()
            every { gameRepo.findById(capture(slot)) } answers {
                if (slot.captured == 42L) {
                    Optional.of(
                        GameEntity()
                    )
                } else {
                    Optional.empty()
                }
            }
            every { gameRepo.existsById(capture(slot)) } answers {
                slot.captured == 42L
            }
            every { ratingRepo.findAll() } returns listOf()

            shouldThrow<IllegalArgumentException> {
                gameService.findById(2)
            }
        }
    }

    @Nested
    inner class PlatformServiceTests {
        @Test
        fun `get all platforms on a single database entry`() {
            every { platformRepo.findAll() } returns listOf(
                PlatformEntity(
                    9,
                    "PC"
                )
            )

            val expectedPlatform = listOf(Platform(9, "PC"))
            val platforms = platformService.findAll()
            platforms.isEmpty() shouldNotBe true
            platforms.count() shouldBe 1
            platforms shouldBe expectedPlatform
        }

        @Test
        fun `get all platforms on multiple database entries`() {
            every { platformRepo.findAll() } returns listOf(
                PlatformEntity(
                    9,
                    "PC"
                ),
                PlatformEntity(
                    7,
                    "PS5"
                )
            )

            val expectedPlatform = listOf(Platform(9, "PC"), Platform(7, "PS5"))
            val platforms = platformService.findAll()
            platforms.isEmpty() shouldNotBe true
            platforms.count() shouldBe 2
            platforms shouldBe expectedPlatform
        }

        @Test
        fun `get all platforms on empty database`() {
            every { platformRepo.findAll() } returns listOf()

            platformService.findAll().isEmpty() shouldBe true
        }
    }
}