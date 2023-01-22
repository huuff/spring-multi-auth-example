package xyz.haff.auth.config

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class PrintConfigCLR(
    private val issuers: Issuers,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Shared secret issuer: ${issuers.sharedSecret}")
        println("Keycloak issuer: ${issuers.keycloak}")
    }
}