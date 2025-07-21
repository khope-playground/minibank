package domain.spi

import application.dto.UserDto

interface UserPort {
    suspend fun getUserByUid(userUid: Long): UserDto
}
