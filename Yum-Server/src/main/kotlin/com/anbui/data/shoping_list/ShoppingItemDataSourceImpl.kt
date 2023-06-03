package com.anbui.data.shoping_list

import com.anbui.data.ingredient.Ingredient
import com.anbui.data.recipe.Recipe
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class ShoppingItemDataSourceImpl(db: CoroutineDatabase) : ShoppingItemDataSource {
    private val shoppingItems = db.getCollection<ShoppingItem>()
    private val ingredients = db.getCollection<Ingredient>()
    private val recipes = db.getCollection<Recipe>()
    override suspend fun insertShoppingList(shoppingItem: ShoppingItem): Boolean {

        return if (shoppingItem.recipeId.isNullOrBlank()) {
            val existItem = shoppingItems.findOne(
                and(
                    ShoppingItem::ingredientId eq shoppingItem.ingredientId,
                    ShoppingItem::userId eq shoppingItem.userId,
                    ShoppingItem::recipeId eq "",
                ),
            )
            if(existItem == null){
                shoppingItems.insertOne(shoppingItem).wasAcknowledged()
            } else {
                changeShoppingListItemStatus(existItem.copy(amount = existItem.amount + 1))
            }
        } else {
            val existItem = shoppingItems.findOne(
                and(
                    ShoppingItem::ingredientId eq shoppingItem.ingredientId,
                    ShoppingItem::userId eq shoppingItem.userId,
                    ShoppingItem::recipeId eq shoppingItem.recipeId,
                ),
            )
            if(existItem == null){
                shoppingItems.insertOne(shoppingItem).wasAcknowledged()
            } else {
//                changeShoppingListItemStatus(existItem.copy(amount = existItem.amount + 1))
                false
            }
        }

    }

    override suspend fun deleteShoppingList(id: String): Boolean {
        return shoppingItems.deleteOne(ShoppingItem::id eq id).wasAcknowledged()
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

    override suspend fun getShoppingListByUserId(id: String): List<ShoppingItemSend> {
        return shoppingItems.find(ShoppingItem::userId eq id).toList().map {
            ShoppingItemSend(
                id = it.id,
                userId = it.userId,
                amount = it.amount,
                isChecked = it.isChecked,
                unit = it.unit,
                ingredient = ingredients.findOne(Ingredient::id eq it.ingredientId)!!,
                recipe = recipes.findOne(Recipe::id eq it.recipeId),
            )
        }
    }
}
