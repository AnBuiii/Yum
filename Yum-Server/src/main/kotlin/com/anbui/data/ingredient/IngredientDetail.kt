package com.anbui.data.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDetail(
    val unit: String = "",
    val amount: Int = 0,
    val note: String = "",
    val ingredientId: String = "",
)
