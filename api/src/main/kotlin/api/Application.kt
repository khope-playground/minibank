package api

import config.CoreConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(CoreConfig::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
