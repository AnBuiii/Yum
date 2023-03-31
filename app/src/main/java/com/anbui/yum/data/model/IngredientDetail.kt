package com.anbui.yum.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDetail(
    val ingredient: String = "",
    val unit: String = "",
    val amount: @Contextual Number,
    val note: String = ""
)
