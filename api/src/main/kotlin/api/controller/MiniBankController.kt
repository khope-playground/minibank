package api.controller

import domain.user.application.dto.UserDto
import domain.apply.domain.spi.ApplyPort
import domain.user.domain.spi.UserPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MiniBankController(
    private val userPort: UserPort,
    private val applyPort: ApplyPort
) {

    @GetMapping("/v1/user/{userUid}")
    suspend fun getUserByUid(@PathVariable userUid: Long): UserDto {
        return userPort.getUserByUid(userUid)
    }
}
