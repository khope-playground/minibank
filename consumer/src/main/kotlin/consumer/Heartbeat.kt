package consumer

import consumer.common.logger
import kotlinx.coroutines.delay

class Heartbeat {
    private val log = logger<Heartbeat>()

    suspend fun start() {
        while (true) {
            log.info("Heartbeat: Application is running...")
            delay(5000) // Delay for 5 seconds
        }
    }
}
