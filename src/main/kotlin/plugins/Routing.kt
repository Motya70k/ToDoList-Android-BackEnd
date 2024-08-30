package ru.shvetsov.todoList.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.shvetsov.todoList.routes.authRouting
import ru.shvetsov.todoList.routes.userRouting
import ru.shvetsov.todoList.services.UserService

fun Application.configureRouting(userService: UserService) {
    routing {
        authRouting(userService)
        userRouting(userService)
    }
}