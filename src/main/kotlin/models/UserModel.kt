package ru.shvetsov.todoList.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val email: String,
    val password: String
)
