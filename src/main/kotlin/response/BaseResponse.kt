package ru.shvetsov.todoList.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse (
    val success: Boolean,
    val message: String
)