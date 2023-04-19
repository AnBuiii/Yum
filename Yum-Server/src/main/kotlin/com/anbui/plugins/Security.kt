package com.anbui.plugins

import ch.qos.logback.core.subst.Token
import com.anbui.security.token.TokenConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.sessions.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*

fun Application.configureSecurity() {
    authentication {
        jwt {
//            realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256(config.secret))
//                    .withAudience(config.audience)
//                    .withIssuer(config.issuer)
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.audience.contains(config.audience))
//                    JWTPrincipal(credential.payload)
//                else null
//            }
        }
    }
}
