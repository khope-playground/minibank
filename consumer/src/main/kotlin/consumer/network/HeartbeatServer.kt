package consumer.network

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import consumer.common.logger
import java.net.InetSocketAddress

class HeartbeatServer(private val port: Int = 9090) {
    private val log = logger<HeartbeatServer>()

    fun start() {
        val server = HttpServer.create(InetSocketAddress(port), 0)
        server.createContext("/healthz", HealthHandler())
        server.executor = null
        server.start()
        log.info("🌐 Health check server running at http://localhost:$port/healthz")
    }

    private class HealthHandler : HttpHandler {
        override fun handle(exchange: HttpExchange) {
            val response = "OK"
            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
            exchange.responseBody.use { it.write(response.toByteArray()) }
        }
    }
}
