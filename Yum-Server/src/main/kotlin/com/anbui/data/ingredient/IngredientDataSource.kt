package com.anbui.data.ingredient

interface IngredientDataSource {
    suspend fun getAllIngredient(): List<Ingredient>

    suspend fun insertIngredient(ingredient: Ingredient): Boolean

    suspend fun getIngredient(id: String): Ingredient?

    suspend fun insertIngredients(newIngredients: List<Ingredient>): Boolean

    suspend fun search(query: String): List<String>
}
