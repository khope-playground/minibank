package external.doamin.user.persistence

import external.doamin.user.domain.model.User

interface UserRepository {

    fun createUser(
        name: String,
        email: String,
        userType: UserType
    ): Long

    fun getUserByUid(
        userUid: Long
    ): User?
}