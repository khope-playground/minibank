package external.doamin.user.presentation

import external.doamin.user.application.UserUseCase
import external.proto.GetUserRequest
import external.proto.GetUserResponse
import external.proto.UserServiceGrpcKt
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class UserGrpcEndpoint(
    private val userUseCase: UserUseCase
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    override suspend fun getUser(request: GetUserRequest): GetUserResponse {
        val user = userUseCase.getUserByUid(request.userUid)
        return GetUserResponse.newBuilder()
            .setUserUid(user.userUid)
            .setName(user.name)
            .setEmail(user.email)
            .setUserType(user.userType.name)
            .build()
    }
}
