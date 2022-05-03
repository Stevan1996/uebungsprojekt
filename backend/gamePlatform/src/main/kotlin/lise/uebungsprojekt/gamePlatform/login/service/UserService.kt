package lise.uebungsprojekt.gamePlatform.login.service

import lise.uebungsprojekt.gamePlatform.login.repository.UserEntity
import lise.uebungsprojekt.gamePlatform.login.repository.UserRepository
import lise.uebungsprojekt.gamePlatform.login.repository.toDomain
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service

interface UserService {
    fun findById(id: Long) : User
    fun getPasswordByEmail(email: String) : String
    fun saveUser(user: UserEntity) : UserEntity
    fun changePassword(user: User, newPassword: String): UserEntity
}

@Service
class UserServiceImpl (private val userRepo: UserRepository) : UserService {
    override fun findById(id: Long): User {
        if (userRepo.existsById(id)) {
            return userRepo.findById(id).map {it.toDomain()}.get()
        } else {
            throw IllegalArgumentException("User not found with id: $id")
        }
    }

    override fun getPasswordByEmail(email: String): String {
        val result = userRepo.findAll().filter { it.email == email }.map { it.password }

        if (result.isEmpty()) {
            throw IllegalArgumentException("User with the email: $email does not exists")
        }

        if (result.size > 1) {
            throw IllegalStateException("Multiple user with the email: $email available")
        }

        return result[0]
    }

    override fun saveUser(user: UserEntity): UserEntity {
        if (userRepo.findAll().any { it.email == user.email }) {
            throw IllegalArgumentException("A user with the email: ${user.email} already exists")
        }
        return userRepo.save(user)
    }

    override fun changePassword(user: User, newPassword: String): UserEntity {
        if (userRepo.findAll().none { it.email == user.email }) {
            throw IllegalArgumentException("A user with the email: ${user.email} does not exists")
        }

        if (BCrypt.checkpw(user.password, getPasswordByEmail(user.email))) {
            throw IllegalArgumentException("Old password does not match")
        }

        val userData = userRepo.findAll().filter { it.email == user.email }[0]
        userRepo.delete(userData)

        return userRepo.save(UserEntity(userData.id, userData.email, BCrypt.hashpw(newPassword, BCrypt.gensalt())))
    }
}