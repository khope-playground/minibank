package api.config

import domain.spi.ApplyPort
import domain.spi.UserPort
import external.proto.LoanApplyServiceGrpcKt
import external.proto.UserServiceGrpcKt
import infra.grpc.client.apply.ApplyGrpcAdapter
import infra.grpc.client.user.UserGrpcAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PortConfig {
    @Bean
    fun userPort(userStub: UserServiceGrpcKt.UserServiceCoroutineStub): UserPort {
        return UserGrpcAdapter(userStub)
    }

    @Bean
    fun applyPort(applyStub: LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineStub): ApplyPort {
        return ApplyGrpcAdapter(applyStub)
    }
}