package external.doamin.user.domain.service

import external.doamin.user.domain.model.User
import external.doamin.user.persistence.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    private val log = LoggerFactory.getLogger(UserService::class.java)

    suspend fun getValidatedUser(userUid: Long): User {
        log.info("get validated user (validation rule: username is not null)")
        val user = userRepository.getUserByUid(userUid)
            ?: throw IllegalArgumentException("User not found with uid: $userUid")

        if (user.name == null) {
            throw IllegalArgumentException("User name cannot be null")
        }

        return user
    }

    suspend fun logEmailByUserUid(userUid: Long) {
        log.info("log email by user uid: $userUid")
        val user = userRepository.getUserByUid(userUid)
            ?: throw IllegalArgumentException("User not found with uid: $userUid")

        log.info("User email: ${user.email}")
    }
}