package com.anbui.security.token

import javax.crypto.SecretKey

data class TokenConfig(
    val issuer: String,
    val audience: String,
    val expireIn: Long,
    val secret: String,
)