package com.anbui.data.ingredient;

import org.litote.kmongo.coroutine.CoroutineDatabase;
import org.litote.kmongo.eq

class IngredientDataSourceImpl(db: CoroutineDatabase) : IngredientDataSource {

    private val ingredients = db.getCollection<Ingredient>()
    override suspend fun getAllIngredient(): List<Ingredient> {
        return ingredients.find().toList()
    }

    override suspend fun insertIngredient(ingredient: Ingredient): Boolean {
        return ingredients.insertOne(ingredient).wasAcknowledged()
    }

    override suspend fun getIngredient(id: String): Ingredient? {
        return ingredients.findOne(Ingredient::id eq id) ?: throw Exception(id)
    }


}
