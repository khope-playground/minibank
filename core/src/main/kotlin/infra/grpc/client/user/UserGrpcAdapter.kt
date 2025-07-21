package infra.grpc.client.user

import application.dto.UserDto
import application.dto.UserType
import domain.spi.UserPort
import external.proto.GetUserRequest
import external.proto.UserServiceGrpcKt

class UserGrpcAdapter(
    private val stub: UserServiceGrpcKt.UserServiceCoroutineStub
) : UserPort {

    override suspend fun getUserByUid(userUid: Long): UserDto {
        val response = stub.getUser(
            GetUserRequest
                .newBuilder()
                .setUserUid(userUid)
                .build()
        )

        return UserDto(
            userUid = response.userUid,
            name = response.name,
            email = response.email,
            userType = UserType.valueOf(response.userType)
        )
    }
}
