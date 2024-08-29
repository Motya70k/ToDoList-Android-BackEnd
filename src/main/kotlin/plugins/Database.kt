package ru.shvetsov.todoList.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.shvetsov.todoList.models.table.UsersTable


object DatabaseFactory {
    private const val DB_URL = "jdbc:mysql://localhost:3307/todo_list_db"
    private const val DRIVER = "com.mysql.cj.jdbc.Driver"
    private const val USER = "root"
    private const val PASSWORD = ""
    fun Application.initializeDatabase() {
        val dataSource = HikariDataSource(config)
        Database.connect(datasource = dataSource)

        transaction {
            SchemaUtils.create(UsersTable)
        }
    }

    private val config = HikariConfig().apply {
        jdbcUrl = DB_URL
        driverClassName = DRIVER
        username = USER
        password = PASSWORD
        maximumPoolSize = 6
        isReadOnly = false
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    suspend fun <T> dbQuery(block: () -> T): T {
        return withContext(Dispatchers.IO) {
            transaction { block() }
        }
    }
}