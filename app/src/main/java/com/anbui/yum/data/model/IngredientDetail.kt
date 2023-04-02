package com.anbui.yum.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDetail(
    val unit: String = "",
    val amount: @Contextual Number = 0,
    val note: String = "",
    val ingredientId: String = "",
)
