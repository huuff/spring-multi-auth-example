package xyz.haff.auth.security.shared_secret

import com.auth0.jwt.algorithms.Algorithm
import java.time.Duration

// TODO: Maybe this from configuration properties
const val SHARED_SECRET = "testsharedsecret123"
const val SHARED_SECRET_ISSUER = "shared-secret-issuer"
val JWT_ALGORITHM: Algorithm = Algorithm.HMAC512(SHARED_SECRET)
