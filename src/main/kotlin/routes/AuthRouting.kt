package ru.shvetsov.todoList.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRouting() {
    get("/hello") {
        call.respondText("Hello")
    }
}