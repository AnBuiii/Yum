package com.anbui.data.shoping_list

import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class ShoppingItemDataSourceImpl(db: CoroutineDatabase) : ShoppingItemDataSource {
    private val shoppingItems = db.getCollection<ShoppingItem>()
    override suspend fun insertShoppingList(shoppingItem: ShoppingItem): Boolean {
        return shoppingItems.insertOne(shoppingItem).wasAcknowledged()
    }

    override suspend fun changeShoppingListItemStatus(shoppingItem: ShoppingItem): Boolean {
        return shoppingItems.updateOne(
            ShoppingItem::id eq shoppingItem.id,
            set(
                ShoppingItem::isChecked setTo shoppingItem.isChecked,
                ShoppingItem::amount setTo shoppingItem.amount,
                ShoppingItem::unit setTo shoppingItem.unit,
            ),
        ).wasAcknowledged()
    }

    override suspend fun getShoppingListByUserId(id: String): List<ShoppingItem> {
        return shoppingItems.find(ShoppingItem::userId eq id).toList()
    }
}
