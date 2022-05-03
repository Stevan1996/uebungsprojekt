package lise.uebungsprojekt.gamePlatform.login.controller

import io.kotest.matchers.shouldBe
import lise.uebungsprojekt.gamePlatform.login.service.User
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class UserDTOToDomainTest {
    @Nested
    inner class UserDTOTest{
        @Test
        fun `user DTO to domain conversion`() {
            val userDTO = UserDTO(42, "example@example.com", "test")
            val userDomain = User(42, "example@example.com", "test")

            userDTO.toDomain() shouldBe userDomain
        }
    }
}