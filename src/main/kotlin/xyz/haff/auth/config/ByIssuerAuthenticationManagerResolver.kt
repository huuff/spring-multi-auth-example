package xyz.haff.auth.config

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver
import reactor.core.publisher.Mono

class ByIssuerAuthenticationManagerResolver(
    private val issuerToResolver: Map<String, ReactiveAuthenticationManager>,
): ReactiveAuthenticationManagerResolver<String> {


    override fun resolve(context: String?): Mono<ReactiveAuthenticationManager> {
        return Mono.justOrEmpty(issuerToResolver[context])
    }
}