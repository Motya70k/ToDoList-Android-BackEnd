package ru.shvetsov.todoList.models

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val login: String,
    val password: String,
    val salt: String
) : Principal
