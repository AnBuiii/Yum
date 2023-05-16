package com.anbui.data.shoping_list

interface ShoppingItemDataSource {
    suspend fun insertShoppingList(shoppingItem: ShoppingItem): Boolean
    suspend fun changeShoppingListItemStatus(shoppingItem: ShoppingItem): Boolean
    suspend fun getShoppingListByUserId(id: String): List<ShoppingItem>
//    suspend fun funChangeShopListItemQuantity(shoppingListItemId: String, isChecked: Boolean)
}
