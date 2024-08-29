package ru.shvetsov.todoList.services

import org.jetbrains.exposed.sql.insert
import ru.shvetsov.todoList.models.UserModel
import ru.shvetsov.todoList.models.table.UsersTable
import ru.shvetsov.todoList.plugins.DatabaseFactory.dbQuery

class UserService {
    suspend fun addUser(user: UserModel) {
        dbQuery {
            UsersTable.insert { table->
                table[email] = user.email
                table[password] = user.password
            }
        }
    }
}
