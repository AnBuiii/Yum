package com.anbui.yum.domain.model

class ShoppingList(
    val id: String = "",
    val amount: Int = 1,
    var foodName: String = "",
    val recipeName: String = "",
    val isChecked: Boolean = false,
    val categoriesName: String = "",
)
