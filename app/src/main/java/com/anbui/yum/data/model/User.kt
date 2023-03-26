package com.anbui.yum.data.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val username: String,
    val password: String,
    val id: String = "",
)
