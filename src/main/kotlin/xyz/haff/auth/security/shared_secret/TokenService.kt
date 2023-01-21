package xyz.haff.auth.security.shared_secret

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.stereotype.Service

@Service
class TokenService {
    fun decode(token: String): UserAuthenticationToken? = try {
        JWT.require(JWT_ALGORITHM).build().verify(token).let { decoded ->
            if (decoded.issuer != SHARED_SECRET_ISSUER) {
                // This token comes from somewhere else (OAuth?), so it's shared secret user
                return null
            }
            UserAuthenticationToken(
                username = decoded.getClaim("username")?.asString() ?: "",
                id = decoded.subject,
                roles = decoded.getClaim("roles")?.asArray(String::class.java)?.toList() ?: listOf(),
                scopes = decoded.getClaim("scopes")?.asArray(String::class.java)?.toList() ?: listOf(),
                token = token,
            )
        }
    } catch (e: JWTVerificationException) {
        null
    }
}
