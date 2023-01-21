package xyz.haff.auth.security.shared_secret

import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest

val ServerHttpRequest.token: String?
    get() {
        val authorization = this.headers.getFirst(HttpHeaders.AUTHORIZATION)
        return if (authorization?.isEmpty() != false)
            null
        else
            authorization.substring("Bearer ".length, authorization.length)
    }
