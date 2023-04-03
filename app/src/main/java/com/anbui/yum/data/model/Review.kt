package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val message: String = "",
    val timestamp: Long = 0L,
    val userId: String = "",
    val recipeId: String = "",
    val id: String,
)
