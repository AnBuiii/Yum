package com.anbui.data.shoping_list

interface ShoppingItemDataSource {
    suspend fun insertShoppingList(shoppingItem: ShoppingItem): Boolean
    suspend fun changeShoppingListItemStatus(shoppingListItemId: String, isChecked: Boolean): Boolean
    suspend fun getShoppingListByUserId(id: String): List<ShoppingItem>
}
