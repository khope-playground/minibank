package external.doamin.user.persistence

import external.doamin.user.domain.model.User

interface UserRepository {
    suspend fun createUser(
        name: String,
        email: String,
        userType: UserType
    ): Long

    suspend fun getUserByUid(
        userUid: Long
    ): User?
}
