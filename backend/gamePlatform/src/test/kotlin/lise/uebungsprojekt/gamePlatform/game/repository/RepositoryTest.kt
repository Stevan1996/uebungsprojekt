package lise.uebungsprojekt.gamePlatform.game.repository

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformEntity
import lise.uebungsprojekt.gamePlatform.game.repository.platform.PlatformRepository
import lise.uebungsprojekt.gamePlatform.game.repository.platform.existsByName
import lise.uebungsprojekt.gamePlatform.game.repository.platform.getPlatformByName
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MariaDBContainerProvider
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
internal class RepositoryTest {
    companion object {
        @Container
        private val container = MariaDBContainerProvider().newInstance("latest").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withUsername("test")
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.password", container::getPassword)
            registry.add("spring.datasource.username", container::getUsername)
        }
    }

    @Autowired
    private lateinit var gameRepo: GameRepository

    @Autowired
    private lateinit var developerRepo: DeveloperRepository

    @Autowired
    private lateinit var platformRepo: PlatformRepository

    @AfterEach
    fun tearDown() {
        gameRepo.deleteAll()
        developerRepo.deleteAll()
        platformRepo.deleteAll()
    }

    @Nested
    inner class GameRepositoryTest {
        @Test
        fun `check for game`() {
            val game = GameEntity(title = "test game")
            gameRepo.save(game)

            gameRepo.existsByName("test game") shouldBe true
        }

        @Test
        fun `check for non-existing game`() {
            gameRepo.existsByName("example game") shouldBe false
        }
    }

    @Nested
    inner class DeveloperRepositoryTest {
        @Test
        fun `get developer by name`() {
            developerRepo.save(DeveloperEntity(null, "test"))

            val result = developerRepo.getDeveloperByName("test")
            result.developer shouldBe "test"
        }

        @Test
        fun `get non-existing developer`() {
            shouldThrow<IllegalArgumentException> {
                developerRepo.getDeveloperByName("test")
            }
        }

        @Test
        fun `check for existing developer`() {
            developerRepo.save(DeveloperEntity(null, "test"))

            developerRepo.existsByName("test") shouldBe true
        }

        @Test
        fun `check for non-existing developer`() {
            developerRepo.existsByName("test") shouldBe false
        }
    }

    @Nested
    inner class PlatformRepositoryTest {
        @Test
        fun `get platform by name`() {
            platformRepo.save(PlatformEntity(null, "test_console"))

            val result = platformRepo.getPlatformByName("test_console")
            result.platform shouldBe "test_console"
        }

        @Test
        fun `get non-existent platform by name`() {
            shouldThrow<IllegalArgumentException> {
                platformRepo.getPlatformByName("test_console")
            }
        }

        @Test
        fun `check for existing platform`() {
            platformRepo.save(PlatformEntity(null, "test_console"))

            platformRepo.existsByName("test_console") shouldBe true
        }

        @Test
        fun `check for non-existing developer`() {
            platformRepo.existsByName("test_console") shouldBe false
        }
    }
}