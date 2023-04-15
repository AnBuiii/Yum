package com.anbui.yum.data.remote.implement

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import com.anbui.yum.data.remote.recipe.RecipeDto
import com.anbui.yum.domain.model.Nutrition
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.data.remote.service.RecipeService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class RecipeServiceImpl(
    private val client: HttpClient,
) : RecipeService {
    override suspend fun getAllRecipe(): List<RecipeDto> {
        return try {
            val a: List<RecipeDto> = client.get("$BASE_URL/recipe"){
                url{
                    parameters.append("page", "3")
                    parameters.append("per_page", "5")

                }
            }.body()
            Log.d("hm", a.toString())
            a
        } catch (e: Exception) {
            Log.d("Recipe service get all recipe error", e.toString())
            listOf()
        }
    }

    override suspend fun getRecipes(page: Int, pageCount: Int): List<RecipeDto> {
        return try {
            val a: List<RecipeDto> = client.get("$BASE_URL/recipe"){
                url{
                    parameters.append("page", page.toString())
                    parameters.append("per_page", pageCount.toString())

                }
            }.body()
            Log.d("hm", a.toString())
            a
        } catch (e: Exception) {
            Log.d("Recipe service get all recipe error", e.toString())
            listOf()
        }
    }

    override suspend fun getNutrition(recipeId: String): Nutrition {
        return try {
            client.get("$BASE_URL/recipe/$recipeId/nutrition").body()
        } catch (e: Exception) {
            Log.d("Recipe service get recipe error", e.toString())
            Nutrition()
        }
    }

    override suspend fun getRecipe(recipeId: String): RecipeDto? {
        return try {
            client.get("$BASE_URL/recipe/$recipeId").body()
        } catch (e: Exception) {
            Log.d("Recipe service get recipe error", e.toString())
            null
        }
    }

}
