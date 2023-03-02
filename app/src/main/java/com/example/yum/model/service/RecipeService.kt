package com.example.yum.model.service

import com.example.yum.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeService {
    val recipes: Flow<List<Recipe>>

    suspend fun getRecipe(recipeId: String): Recipe?

    suspend fun save(recipe: Recipe): String
    suspend fun update(recipe: Recipe)
    suspend fun delete(recipe: Recipe)
    suspend fun deleteAllForUser(userId : String)

}