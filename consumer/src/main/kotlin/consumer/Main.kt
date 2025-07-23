package consumer

import consumer.common.logger
import consumer.config.ConsumerAppConfig
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConsumerAppMain

private val log = logger<ConsumerAppMain>()

fun main(): Unit = runBlocking {
    log.info("Initializing Consumer Application...")
    val applicationContext = AnnotationConfigApplicationContext(ConsumerAppConfig::class.java)
    val appServer = applicationContext.getBean(ConsumerAppServer::class.java)

    Runtime.getRuntime().addShutdownHook(
        Thread {
            log.info("*** Shutting down Consumer Application ***")
            appServer.stop()
            log.info("*** Consumer Application shut down ***")
        }
    )

    appServer.start()
}
