package com.anbui.yum.data.remote.shopping_list

interface ShoppingService {
    suspend fun getShoppingList(userId: String): List<ShoppingItemDto>
    suspend fun changeShoppingItemStatus(shoppingItemSendDto: ShoppingItemSendDto): Boolean
    suspend fun addShoppingItem(shoppingItemSendDto: ShoppingItemSendDto): Boolean
    suspend fun removeShoppingItem(id: String): Boolean
}
