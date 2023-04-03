package com.anbui.yum.data.remote.implement

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.data.remote.service.RecipeService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RecipeServiceImpl(
    private val client: HttpClient,
) : RecipeService {
    override suspend fun getAllRecipe(): List<Recipe> {
        return try {
            val a: List<Recipe> = client.get("$BASE_URL/recipe").body()
            Log.d("hm", a.toString())
            a
        } catch (e: Exception) {
            Log.d("Recipe service get all recipe error", e.toString())
            listOf()
        }
    }

    override suspend fun getRecipe(recipeId: String): Recipe? {
        return try {
            client.get("$BASE_URL/recipe/$recipeId").body()
        } catch (e: Exception) {
            Log.d("Recipe service get recipe error", e.toString())
            null
        }
    }

}
