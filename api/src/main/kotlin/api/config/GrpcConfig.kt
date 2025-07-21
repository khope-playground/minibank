package api.config

import domain.spi.ApplyPort
import domain.spi.UserPort
import external.proto.LoanApplyServiceGrpcKt.LoanApplyServiceCoroutineStub
import external.proto.UserServiceGrpcKt.UserServiceCoroutineStub
import infra.grpc.client.apply.ApplyGrpcAdapter
import infra.grpc.client.user.UserGrpcAdapter
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfig {
    @Bean
    fun grpcChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress("localhost", 9000)
        .usePlaintext()
        .build()

    @Bean
    fun userStub(channel: ManagedChannel): UserServiceCoroutineStub {
        return UserServiceCoroutineStub(channel)
    }

    @Bean
    fun applyStub(channel: ManagedChannel): LoanApplyServiceCoroutineStub {
        return LoanApplyServiceCoroutineStub(channel)
    }
}