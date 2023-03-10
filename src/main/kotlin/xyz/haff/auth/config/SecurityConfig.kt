package xyz.haff.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerReactiveAuthenticationManagerResolver
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Flux
import xyz.haff.auth.security.shared_secret.SharedSecretAuthenticationManager

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(proxyTargetClass = true)
class SecurityConfig(
    private val issuers: Issuers,
) {


    fun jwtAuthenticationConverter(): ReactiveJwtAuthenticationConverter {
        val grantedAuthoritiesConverter = Converter<Jwt, Flux<GrantedAuthority>> { source ->
            Flux.concat(
                Flux.fromIterable(
                    ((source.claims["realm_access"] as Map<String, Any>)["roles"] as List<String>)
                ).map { SimpleGrantedAuthority("ROLE_$it") },
                Flux.fromIterable(
                    ((source.claims["scope"] as String).split(" "))
                ).map { SimpleGrantedAuthority("SCOPE_$it") },
            )
        }

        val jwtAuthenticationConverter = ReactiveJwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter)
        return jwtAuthenticationConverter
    }


    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity,
        sharedSecretAuthenticationManager: SharedSecretAuthenticationManager,
    ): SecurityWebFilterChain {
        return http.invoke {
            authorizeExchange { authorize(anyExchange, permitAll) }
            csrf { disable() }
            logout { disable() }
            httpBasic { disable() }
            oauth2ResourceServer {
                authenticationManagerResolver = JwtIssuerReactiveAuthenticationManagerResolver(
                    ByIssuerAuthenticationManagerResolver(
                        mapOf(
                            issuers.sharedSecret to sharedSecretAuthenticationManager,
                            issuers.keycloak to JwtReactiveAuthenticationManager(
                                ReactiveJwtDecoders.fromIssuerLocation(issuers.keycloak)
                            ).apply {
                                setJwtAuthenticationConverter(jwtAuthenticationConverter())
                            }
                        )
                    )
                )
            }
        }
    }
}