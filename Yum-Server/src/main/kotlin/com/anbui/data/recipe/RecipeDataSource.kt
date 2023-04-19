package com.anbui.data.recipe

interface RecipeDataSource {
    suspend fun getRecipes(page: Int = 1, pageCount: Int = Int.MAX_VALUE): List<Recipe>

    suspend fun insertRecipe(recipe: Recipe): Boolean

    suspend fun getRecipe(id: String): Recipe?

    suspend fun getNutrition(id: String): Nutrition
}
