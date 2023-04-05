package com.anbui.yum.data.remote.service

import com.anbui.yum.data.model.Nutrition
import com.anbui.yum.data.model.Recipe

interface RecipeService{

    suspend fun getRecipe(recipeId: String) : Recipe?
    suspend fun getAllRecipe() : List<Recipe>
    suspend fun getNutrition(recipeId: String): Nutrition

}
