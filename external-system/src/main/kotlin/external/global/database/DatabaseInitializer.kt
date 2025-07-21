package external.global.database

import com.zaxxer.hikari.HikariDataSource
import external.doamin.apply.persistence.LoanApplyTable
import external.doamin.user.persistence.UserTable
import jakarta.annotation.PostConstruct
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DatabaseInitializer(
    private val dataSource: DataSource
) {

    private val log = LoggerFactory.getLogger(DatabaseInitializer::class.java)

    @PostConstruct
    fun init() {
        log.info("Initializing database connection and creating tables...")
        Database.connect(dataSource as HikariDataSource)

        transaction {
            log.info("Creating tables...")
            SchemaUtils.create(
                UserTable, LoanApplyTable
            )
        }
    }
}