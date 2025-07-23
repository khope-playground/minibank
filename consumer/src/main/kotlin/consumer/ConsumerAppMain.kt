package consumer

import ch.qos.logback.classic.util.ContextInitializer
import consumer.common.logger
import consumer.config.ConsumerAppConfig
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object ConsumerAppMain {

    private val log = logger<ConsumerAppMain>()

    init {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "logback.xml")
    }

    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
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
}
