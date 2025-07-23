package consumer

import consumer.common.logger
import consumer.network.Heartbeat
import consumer.network.HeartbeatServer
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConsumerAppServer(
    private val heartbeater: Heartbeat,
    private val server: HeartbeatServer
) {
    private val log = logger<ConsumerAppServer>()
    private var job: Job? = null

    fun start() = runBlocking {
        log.info("Start Consumer Application on port 9090")

        job = coroutineScope {
            launch { server.start() }
            launch { heartbeater.start() }
        }
    }

    fun stop() {
        log.info("Stopping Consumer Application...")
        job?.cancel()
        log.info("Consumer Application stopped.")
    }
}
