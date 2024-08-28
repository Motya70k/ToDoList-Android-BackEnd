package ru.shvetsov.todoList.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.shvetsov.todoList.routes.authRouting

fun Application.configureRouting() {
    routing {
        authRouting()
    }
}