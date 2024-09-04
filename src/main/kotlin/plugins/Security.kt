package ru.shvetsov.todoList.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import ru.shvetsov.todoList.services.UserService
import ru.shvetsov.todoList.utils.jwt.JwtService

fun Application.configureSecurity(userService: UserService) {
    authentication {
        jwt("jwt") {
            verifier(JwtService.getVerifier())
            realm = "Service Server"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val user = userService.getUserByEmail(email = email)
                user
            }
        }
    }
}