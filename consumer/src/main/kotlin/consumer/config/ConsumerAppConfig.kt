package consumer.config

import config.CoreConfig
import consumer.ConsumerAppServer
import consumer.network.Heartbeat
import consumer.network.HeartbeatServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(CoreConfig::class)
class ConsumerAppConfig {

    @Bean
    fun consumerAppServer() = ConsumerAppServer(
        heartbeater = Heartbeat(),
        server = HeartbeatServer(9090) // Assuming HeartbeatServer is defined elsewhere
    )
}
