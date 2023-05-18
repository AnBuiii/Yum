package com.anbui.data.shoping_list

interface ShoppingItemDataSource {
    suspend fun insertShoppingList(shoppingItem: ShoppingItem): Boolean

    /**
     * change isChecked, amount, unit by Ingredient::id
     */
    suspend fun changeShoppingListItemStatus(shoppingItem: ShoppingItem): Boolean
    suspend fun getShoppingListByUserId(id: String): List<ShoppingItemSend>
//    suspend fun funChangeShopListItemQuantity(shoppingListItemId: String, isChecked: Boolean)
    suspend fun deleteShoppingList(id: String): Boolean
}
