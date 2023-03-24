package com.example.yum.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String,
)