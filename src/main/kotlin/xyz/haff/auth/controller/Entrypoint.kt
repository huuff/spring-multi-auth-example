package xyz.haff.auth.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Entrypoint {

    @GetMapping("/auth")
    @PreAuthorize("isAuthenticated()")
    suspend fun authenticated(): ResponseEntity<String> {
        return ResponseEntity.ok("Only authenticated requests can go through")
    }

    @PreAuthorize("hasAuthority('SCOPE_sample-scope')")
    @GetMapping("/scoped")
    suspend fun scoped(): ResponseEntity<String> {
        return ResponseEntity.ok("User has 'sample-scope'")
    }

    @GetMapping("/unauth")
    suspend fun unauth(): ResponseEntity<String> {
        return ResponseEntity.ok("Anybody can access this")
    }
}