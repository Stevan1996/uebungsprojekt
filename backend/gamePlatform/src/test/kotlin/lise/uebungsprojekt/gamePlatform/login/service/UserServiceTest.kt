package lise.uebungsprojekt.gamePlatform.login.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import lise.uebungsprojekt.gamePlatform.login.repository.UserEntity
import lise.uebungsprojekt.gamePlatform.login.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

internal class UserServiceTest {
    private val userRepo = mockk<UserRepository>()
    private val userService = UserServiceImpl(userRepo)

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Nested
    inner class GetDataFromServiceTest {
        @Test
        fun `get user by id`() {
            val sampleUserEntity = UserEntity(42, "sample@example.com", "test")

            val slot = slot<Long>()
            every { userRepo.findById(capture(slot)) } answers {
                if (slot.captured == 42L) {
                    Optional.of(sampleUserEntity)
                } else {
                    Optional.empty()
                }
            }
            every { userRepo.existsById(capture(slot)) } answers {
                slot.captured == 42L
            }

            val expectedUser = User(42, "sample@example.com", "test")

            userService.findById(42) shouldBe expectedUser
        }

        @Test
        fun `get user by non-existing id`() {
            val sampleUserEntity = UserEntity(42, "sample@example.com", "test")

            val slot = slot<Long>()
            every { userRepo.findById(capture(slot)) } answers {
                if (slot.captured == 42L) {
                    Optional.of(sampleUserEntity)
                } else {
                    Optional.empty()
                }
            }
            every { userRepo.existsById(capture(slot)) } answers {
                slot.captured == 42L
            }

            val expectedUser = User(42, "sample@example.com", "test")

            shouldThrow<IllegalArgumentException> {
                userService.findById(1)
            }
        }

        @Test
        fun `retrieve password on existing user`() {
            every { userRepo.findAll() } returns listOf(
                UserEntity(42, "sample@example.com", "test"),
                UserEntity(4, "anotherSample@example.com", "test2")
            )

            userService.getPasswordByEmail("sample@example.com") shouldBe "test"
        }

        @Test
        fun `retrieve password on non-existing user`() {
            every { userRepo.findAll() } returns listOf(
                UserEntity(42, "sample@example.com", "test"),
                UserEntity(4, "anotherSample@example.com", "test2")
            )

            shouldThrow<IllegalArgumentException> {
                userService.getPasswordByEmail("example@example.com")
            }
        }

        @Test
        fun `retrieve password from empty database`() {
            every { userRepo.findAll() } returns listOf()

            shouldThrow<IllegalArgumentException> {
                userService.getPasswordByEmail("example@example.com")
            }
        }

        @Test
        fun `retrieve password with ambiguous email`() {
            every { userRepo.findAll() } returns listOf(
                UserEntity(42, "sample@example.com", "test"),
                UserEntity(4, "sample@example.com", "test2")
            )

            shouldThrow<IllegalStateException> {
                userService.getPasswordByEmail("sample@example.com")
            }
        }
    }
}