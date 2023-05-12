package com.anbui.yum.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val message: String = "",
    val timestamp: Long = 0L,
    val rating: Int = 0,
    val userId: String = "",
    val recipeId: String = "",
    val id: String = "",
)
