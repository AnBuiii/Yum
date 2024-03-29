package com.anbui.yum.data.remote.recipe

import com.anbui.yum.domain.model.Nutrition

interface RecipeService{
    suspend fun getRecipe(recipeId: String) : RecipeDto
    suspend fun getRecipes(page: Int, pageCount: Int) : List<RecipeDto>
    suspend fun getAllRecipe() : List<RecipeDto>
    suspend fun getNutrition(recipeId: String): Nutrition
    suspend fun getAllRecipeSize(): Int
    suspend fun search(query: String): List<RecipeDto>
    suspend fun getRecipeByCategory(name: String): List<RecipeDto>

}
