package ru.shvetsov.todoList.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.shvetsov.todoList.models.UserModel
import ru.shvetsov.todoList.requests.UserRequest
import ru.shvetsov.todoList.services.UserService

fun Route.userRouting(userService: UserService) {

    post("/add-user") {
        val userRequest = call.receiveNullable<UserRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        try {
            val user = UserModel(
                id = 0,
                email = userRequest.email,
                password = userRequest.password
            )
            if (user.email.isNotBlank() && user.password.isNotBlank()) {
                userService.addUser(user = user)
                call.respond(HttpStatusCode.OK)
            }

        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict)
        }
    }
}