package ru.shvetsov.todoList.models

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val email: String,
    val password: String,
    val salt: String
) : Principal
