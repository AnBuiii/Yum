package com.anbui.data.recipe;

import com.anbui.data.ingredient.Ingredient
import org.litote.kmongo.coroutine.CoroutineDatabase;
import org.litote.kmongo.eq
import org.litote.kmongo.regex

class RecipeDataSourceImpl(db: CoroutineDatabase) : RecipeDataSource {

    private val recipes = db.getCollection<Recipe>()
    private val ingredients = db.getCollection<Ingredient>()
    override suspend fun getRecipes(page: Int, pageCount: Int): List<Recipe> {
        return recipes.find().skip(skip = (page - 1) * pageCount).limit(limit = pageCount).toList()
    }

    override suspend fun getRecipe(id: String): Recipe? {
        return recipes.findOne(Recipe::id eq id)
    }

    override suspend fun getNutrition(id: String): Nutrition {
        val recipe = getRecipe(id)
        try {

        } catch (e: Exception) {

        }
        TODO()
    }

    override suspend fun search(query: String): List<Recipe> {
        return recipes.find(Recipe::title regex "(?i)$query").limit(10).toList()
    }

    override suspend fun insertRecipe(recipe: Recipe): Boolean {
        return recipes.insertOne(recipe).wasAcknowledged()
    }

}
