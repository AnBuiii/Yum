package com.anbui.data.ingredient;

import org.litote.kmongo.coroutine.CoroutineDatabase;
import org.litote.kmongo.eq
import org.litote.kmongo.regex

class IngredientDataSourceImpl(db: CoroutineDatabase) : IngredientDataSource {

    private val ingredients = db.getCollection<Ingredient>()
    override suspend fun getAllIngredient(): List<Ingredient> {
        return ingredients.find().toList()
    }

    override suspend fun insertIngredient(ingredient: Ingredient): Boolean {
        return ingredients.insertOne(ingredient).wasAcknowledged()
    }

    override suspend fun getIngredient(id: String): Ingredient? {
        return ingredients.findOne(Ingredient::id eq id)
    }

    override suspend fun search(query: String): List<Ingredient> {
        return ingredients.find(Ingredient::name regex "(?i)$query").limit(10).toList()
    }

    override suspend fun insertIngredients(newIngredients: List<Ingredient>): Boolean {
        return ingredients.insertMany(newIngredients).wasAcknowledged()
    }


}
