package ru.shvetsov.todoList.utils.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import ru.shvetsov.todoList.models.UserModel
import java.time.LocalDateTime
import java.time.ZoneOffset
object JwtService {

    private const val SECRET = "secret"
    private const val ISSUER = "todoList"

    fun generateToken(user: UserModel): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withClaim("email", user.email)
            .withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC))
            .sign(Algorithm.HMAC256(SECRET))
    }

    fun getVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(SECRET))
            .withIssuer(ISSUER)
            .build()
    }
}