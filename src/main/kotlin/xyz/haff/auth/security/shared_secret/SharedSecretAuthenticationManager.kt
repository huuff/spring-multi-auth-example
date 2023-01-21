package xyz.haff.auth.security.shared_secret

import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class SharedSecretAuthenticationManager(
    private val tokenService: TokenService,
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        if (authentication !is BearerTokenAuthenticationToken)
            return Mono.empty()

        return mono { tokenService.decode(authentication.token) }
    }
}
