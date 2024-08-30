package ru.shvetsov.todoList.models.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UsersTable: Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val email: Column<String> = varchar("email", 100).uniqueIndex()
    val password: Column<String> = varchar("password", 100)
    val salt: Column<String> = varchar("salt", 50)

    override val primaryKey = PrimaryKey(id)
}