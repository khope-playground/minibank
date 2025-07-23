package config

import domain.spi.ApplyPort
import domain.spi.UserPort
import external.proto.LoanApplyServiceGrpcKt
import external.proto.UserServiceGrpcKt
import infra.grpc.client.apply.ApplyGrpcAdapter
import infra.grpc.client.user.UserGrpcAdapter
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoreConfig {

    /**
     * grpc stub
     */
    @Bean
    fun grpcChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress("localhost", 9000)
        .usePlaintext()
        .build()

    @Bean
    fun userStub(channel: ManagedChannel): UserServiceGrpcKt.UserServiceCoroutineStub {
        return UserServiceGrpcKt.UserServiceCoroutineStub(channel)
    }

    @Bean
    fun applyStub(channel: ManagedChannel): LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineStub {
        return LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineStub(channel)
    }

    /**
     * adapter
     */
    @Bean
    fun userPort(userStub: UserServiceGrpcKt.UserServiceCoroutineStub): UserPort {
        return UserGrpcAdapter(userStub)
    }

    @Bean
    fun applyPort(applyStub: LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineStub): ApplyPort {
        return ApplyGrpcAdapter(applyStub)
    }
}