package external.doamin.user.application

import external.doamin.user.domain.model.User
import external.doamin.user.domain.service.UserService
import org.springframework.stereotype.Component

@Component
class UserUseCase(
    private val userService: UserService
) {
    suspend fun getUserByUid(userUid: Long): User {
        val user = userService.getValidatedUser(userUid)
        userService.logEmailByUserUid(userUid)
        return user
    }
}
