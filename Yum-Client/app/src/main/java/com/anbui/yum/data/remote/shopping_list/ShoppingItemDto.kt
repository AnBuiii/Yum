package com.anbui.yum.data.remote.shopping_list

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItemDto(
    val userId: String = "",
    val ingredientId: String = "",
    val recipeId: String? = "",
    val amount: Double = 0.0,
    val isChecked: Boolean = false,
    val id: String = "",
)
