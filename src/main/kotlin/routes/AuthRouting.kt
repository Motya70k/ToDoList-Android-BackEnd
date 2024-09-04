package ru.shvetsov.todoList.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.shvetsov.todoList.models.UserModel
import ru.shvetsov.todoList.requests.LoginRequest
import ru.shvetsov.todoList.requests.UserRequest
import ru.shvetsov.todoList.services.UserService
import ru.shvetsov.todoList.utils.jwt.JwtService
import ru.shvetsov.todoList.utils.security.PasswordEncryptor.verifyPassword

fun Route.authRouting(
    userService: UserService,
) {
    post("/register") {
        val userRequest = call.receiveNullable<UserRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        try {
            val user = UserModel(
                id = 0,
                email = userRequest.email,
                password = userRequest.password,
                salt = ""
            )
            if (user.email.isNotBlank() && user.password.isNotBlank()) {
                userService.addUser(user = user)
                call.respond(HttpStatusCode.OK)
            }

        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict)
        }
    }

    post("/login") {
        val loginRequest = call.receiveNullable<LoginRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        try {
            val user = userService.getUserByEmail(loginRequest.email)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound)
            }
            if (verifyPassword(loginRequest.password, user?.salt!!, user.password)) {
                call.respond(JwtService.generateToken(user))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict)
        }
    }
}