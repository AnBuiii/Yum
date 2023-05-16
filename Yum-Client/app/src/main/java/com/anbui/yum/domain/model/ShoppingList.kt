package com.anbui.yum.domain.model

data class ShoppingList(
    val id: String = "",
    val amount: Int = 1,
    val foodName: String = "",
    val recipeName: String = "",
    val isChecked: Boolean = false,
    val unit: String = "unit",
    val categoriesName: String = "",
)
