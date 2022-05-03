package lise.uebungsprojekt.gamePlatform.login.service

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import lise.uebungsprojekt.gamePlatform.login.controller.UserDTO
import lise.uebungsprojekt.gamePlatform.login.repository.UserEntity
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mindrot.jbcrypt.BCrypt

internal class UserDomainTest {
    @Nested
    inner class UserDomainToEntityTest{
        @Test
        fun `user domain to entity conversion`() {
            val userDomain = User(42, "example@example.com", "hi")
            val userEntity = UserEntity(42, "example@example.com", "hi")
            val convertedData = userDomain.toEntity()

            convertedData.id shouldBe userEntity.id
            convertedData.email shouldBe userEntity.email
            BCrypt.checkpw(userEntity.password, convertedData.password) shouldBe true
        }
    }

    @Nested
    inner class UserDomainToDTOTest{
        @Test
        fun `user domain to DTO conversion`() {
            val userDomain = User(42, "example@example.com", "hi")
            val userDTO = UserDTO(42, "example@example.com", "hi")

            userDomain.toDTO() shouldBe userDTO
        }
    }
}