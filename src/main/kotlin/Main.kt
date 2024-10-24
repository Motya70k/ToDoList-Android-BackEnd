package ru.shvetsov.todoList

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.shvetsov.todoList.plugins.DatabaseFactory.initializeDatabase
import ru.shvetsov.todoList.plugins.configureRouting
import ru.shvetsov.todoList.plugins.configureSecurity
import ru.shvetsov.todoList.plugins.configureSerialization
import ru.shvetsov.todoList.services.UserService

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module, host = "192.168.1.111")
        .start(wait = true)
}

fun Application.module() {
    val userService = UserService()
    configureSecurity(userService)
    configureRouting(userService = userService)
    initializeDatabase()
    configureSerialization()
}