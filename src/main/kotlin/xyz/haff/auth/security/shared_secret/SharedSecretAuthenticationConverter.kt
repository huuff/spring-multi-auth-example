package xyz.haff.auth.security.shared_secret

import com.auth0.jwt.JWT
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import org.springframework.stereotype.Component

@Component
class SharedSecretAuthenticationConverter: ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        val token = exchange.request.token ?: return Mono.empty()

        // TODO: This decoding is blocking!!!!
        return if (JWT.decode(token).issuer == SHARED_SECRET_ISSUER) {
            Mono.just(NotYetValidatedSharedSecretToken(token))
        } else {
            Mono.empty()
        }
    }
}
