package com.anbui.yum.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDetail(
    val unit: String = "",
    val amount: Int,
    val note: String = "",
    val ingredientId: String = "",
)
