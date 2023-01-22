package xyz.haff.auth.security.shared_secret

import com.auth0.jwt.algorithms.Algorithm

// TODO: Maybe this from configuration properties
const val SHARED_SECRET = "testsharedsecret123"
val JWT_ALGORITHM: Algorithm = Algorithm.HMAC512(SHARED_SECRET)
