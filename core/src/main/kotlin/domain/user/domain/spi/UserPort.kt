package domain.user.domain.spi

import domain.user.application.dto.UserDto

interface UserPort {
    suspend fun getUserByUid(userUid: Long): UserDto
}
