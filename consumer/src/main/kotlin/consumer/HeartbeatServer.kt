package consumer

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

class HeartbeatServer(
    private val port: Int
) {

    private val log = consumer.common.logger<HeartbeatServer>()

    fun start() {
        val server = HttpServer.create(InetSocketAddress(port), 0)
        server.createContext("/heartbeat") { exchange ->
            val response = "Application is running"
            exchange.sendResponseHeaders(200, response.length.toLong())
            exchange.responseBody.use { it.write(response.toByteArray()) }
            exchange.close()
        }
        server.executor = null
        server.start()
        log.info("Heartbeat server started on port $port")
    }

    class HealthHandler : HttpHandler {
        override fun handle(exchange: HttpExchange) {
            val response = "OK"
            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
            exchange.responseBody.use { it.write(response.toByteArray()) }
        }
    }
}