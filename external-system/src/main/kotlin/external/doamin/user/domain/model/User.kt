package external.doamin.user.domain.model

import external.doamin.user.persistence.UserType

data class User(
    val userUid: Long = 0,
    val name: String,
    val email: String,
    val userType: UserType
)
