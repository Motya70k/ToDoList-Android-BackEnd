package ru.shvetsov.todoList.services

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.shvetsov.todoList.models.UserModel
import ru.shvetsov.todoList.models.table.UsersTable
import ru.shvetsov.todoList.plugins.DatabaseFactory.dbQuery
import ru.shvetsov.todoList.utils.security.PasswordEncryptor.generateSalt
import ru.shvetsov.todoList.utils.security.PasswordEncryptor.hashPassword

class UserService {
    suspend fun addUser(user: UserModel) {
        val generatedSalt = generateSalt()
        val hashPassword = hashPassword(user.password, generatedSalt)
        dbQuery {
            UsersTable.insert { table ->
                table[email] = user.login
                table[password] = hashPassword
                table[salt] = generatedSalt
            }
        }
    }

    suspend fun deleteUser(id: Int) {
        dbQuery {
            UsersTable.deleteWhere { UsersTable.id eq id }
        }
    }

    suspend fun updateUser(user: UserModel) {
        dbQuery {
            UsersTable.update({ UsersTable.id eq user.id }) { table ->
                table[email] = user.login
                table[password] = user.password
            }
        }
    }

    suspend fun getUserById(id: Int): UserModel? {
        return dbQuery {
            UsersTable.selectAll().where { UsersTable.id eq id }
                .mapNotNull { rowToUserModel(it) }
                .singleOrNull()
        }
    }

    suspend fun getUserByEmail(email: String): UserModel? {
        return dbQuery {
            UsersTable.selectAll().where { UsersTable.email eq email }
                .mapNotNull { rowToUserModel(it) }
                .singleOrNull()
        }
    }

    private fun rowToUserModel(row: ResultRow?): UserModel? {
        if (row == null) {
            return null
        }
        return UserModel(
            id = row[UsersTable.id],
            login = row[UsersTable.email],
            password = row[UsersTable.password],
            salt = row[UsersTable.salt]
        )
    }
}
