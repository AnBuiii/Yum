package com.anbui.yum.data.remote.shopping_list

interface ShoppingService {
    suspend fun getShoppingList(userId: String): List<ShoppingItemDto>
    suspend fun changeShoppingItemStatus(id : String, isChecked: Boolean)
}
