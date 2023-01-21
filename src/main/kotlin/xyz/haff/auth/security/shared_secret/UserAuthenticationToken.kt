package xyz.haff.auth.security.shared_secret

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

class UserAuthenticationToken(
    val id: String,
    val username: String,
    val roles: List<String>,
    val scopes: List<String>,
    val token: String? = null,
) : AbstractAuthenticationToken(
    roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()
    + scopes.map { SimpleGrantedAuthority("SCOPE_$it")}.toMutableList()
) {
    override fun getCredentials(): Any? = token
    override fun getPrincipal(): Any? = null
    override fun isAuthenticated(): Boolean = true
}
