package lise.uebungsprojekt.gamePlatform.login.repository

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import lise.uebungsprojekt.gamePlatform.login.service.User
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class UserEntityToDomainTest{
    @Nested
    inner class UserEntityTest{
        @Test
        fun `user entity to domain conversion`() {
            val userEntity = UserEntity(42, "example@example.com", "123F")
            val userDomain = User(42, "example@example.com", "123F")

            userEntity.toDomain() shouldBe userDomain
        }

        @Test
        fun `user entity to domain conversion with different id`() {
            val userEntity = UserEntity(42, "example@example.com", "123F")
            val userDomain = User(-1, "example@example.com", "123F")

            userEntity.toDomain() shouldNotBe userDomain
        }

        @Test
        fun `user entity to domain conversion with different email`() {
            val userEntity = UserEntity(42, "example@example.com", "123F")
            val userDomain = User(42, "example@google.com", "123F")

            userEntity.toDomain() shouldNotBe userDomain
        }

        @Test
        fun `user entity to domain conversion with different different password`() {
            val userEntity = UserEntity(42, "example@example.com", "123F")
            val userDomain = User(42, "example@example.com", "845315")

            userEntity.toDomain() shouldNotBe userDomain
        }
    }
}