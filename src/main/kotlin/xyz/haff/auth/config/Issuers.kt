package xyz.haff.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "token.issuers")
class Issuers(
    val sharedSecret: String,
    val keycloak: String,
)
