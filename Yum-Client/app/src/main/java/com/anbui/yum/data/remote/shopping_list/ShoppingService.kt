package com.anbui.yum.data.remote.shopping_list

import kotlinx.coroutines.flow.Flow

interface ShoppingService {
    suspend fun getShoppingList(userId: String): List<ShoppingItemDto>
    suspend fun changeShoppingItemStatus(shoppingItemDto: ShoppingItemDto) : Boolean
}
