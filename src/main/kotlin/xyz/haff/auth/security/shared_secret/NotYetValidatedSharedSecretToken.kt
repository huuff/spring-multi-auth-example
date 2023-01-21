package xyz.haff.auth.security.shared_secret

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class NotYetValidatedSharedSecretToken(
    val jwtToken: String,
) : Authentication {
    override fun getName(): String? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getCredentials(): Any = jwtToken

    override fun getDetails(): Any? = null

    override fun getPrincipal(): Any? = null

    override fun isAuthenticated(): Boolean = false

    override fun setAuthenticated(isAuthenticated: Boolean) {

    }
}
