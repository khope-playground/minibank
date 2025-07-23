package domain.user.application.dto

data class UserDto(
    val userUid: Long = 0L,
    val name: String = "",
    val email: String = "",
    val userType: UserType
)

enum class UserType {
    ADMIN, INVESTOR, BORROWER
}
