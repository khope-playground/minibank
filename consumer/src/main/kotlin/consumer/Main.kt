package consumer

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConsumerAppMain

private val log = consumer.common.logger<ConsumerAppMain>()

fun main(): Unit = runBlocking {
    log.info("Starting Consumer Application...")
    val server = HeartbeatServer(9090)
    val heartbeat = Heartbeat()

    coroutineScope {
        launch { server.start() }
        launch { heartbeat.start() }
    }
}