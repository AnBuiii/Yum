package com.anbui.data.shoping_list

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

class ShoppingItemDataSourceImpl(db: CoroutineDatabase) : ShoppingItemDataSource {
    private val shoppingItems = db.getCollection<ShoppingItem>()
    override suspend fun insertShoppingList(shoppingItem: ShoppingItem): Boolean {
        return shoppingItems.insertOne(shoppingItem).wasAcknowledged()
    }

    override suspend fun changeShoppingListItemStatus(shoppingListItemId: String, isChecked: Boolean): Boolean {
        return shoppingItems.updateOne(
            ShoppingItem::id eq shoppingListItemId,
            setValue(ShoppingItem::isChecked, isChecked),
        ).wasAcknowledged()
    }

    override suspend fun getShoppingListByUserId(id: String): List<ShoppingItem> {
        return shoppingItems.find(ShoppingItem::userId eq id).toList()
    }
}
