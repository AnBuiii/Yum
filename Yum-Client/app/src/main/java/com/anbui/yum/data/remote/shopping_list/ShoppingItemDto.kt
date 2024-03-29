package com.anbui.yum.data.remote.shopping_list

import com.anbui.yum.domain.model.Ingredient
import com.anbui.yum.domain.model.Recipe
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItemDto(
    val userId: String = "",
    val ingredient: Ingredient,
    val recipe: Recipe?,
    val amount: Double = 0.0,
    val unit: String = "unit",
    val isChecked: Boolean = false,
    val id: String = "",
)
