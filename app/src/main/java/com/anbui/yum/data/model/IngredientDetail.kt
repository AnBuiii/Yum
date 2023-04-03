package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDetail(
    val unit: String = "",
    val amount: Int,
    val note: String = "",
    val ingredientId: String = "",
)
