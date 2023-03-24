package com.example.yum.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId: String,
    val name: String,
    val title: String
)