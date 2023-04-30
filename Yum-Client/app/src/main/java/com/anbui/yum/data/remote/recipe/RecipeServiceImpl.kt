package com.anbui.yum.data.remote.recipe

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import com.anbui.yum.domain.model.Nutrition
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RecipeServiceImpl(
    private val client: HttpClient,
) : RecipeService {
    override suspend fun getAllRecipe(): List<RecipeDto> {
        return try {
            val a: List<RecipeDto> = client.get("$BASE_URL/recipe").body()
            Log.d(
                "hm",
                a.toString(),
            )
            a
        } catch (e: Exception) {
            Log.d(
                "Recipe service get all recipe error",
                e.toString(),
            )
            listOf()
        }
    }

    override suspend fun getRecipes(page: Int, pageCount: Int): List<RecipeDto> {
        val a: List<RecipeDto> = client.get("$BASE_URL/recipe") {
            url {
                parameters.append(
                    "page",
                    page.toString(),
                )
                parameters.append(
                    "per_page",
                    pageCount.toString(),
                )

            }
        }.body()
        Log.d(
            "hm",
            a.toString(),
        )
        return a
//        }
//        catch (e: Exception) {
//            Log.d("Recipe service get all recipe error", e.toString())
//            listOf()
//        }
    }

    override suspend fun getAllRecipeSize(): Int {
        return client.get("$BASE_URL/recipe/size").body()
    }

    override suspend fun getNutrition(recipeId: String): Nutrition {
        return try {
            client.get("$BASE_URL/recipe/$recipeId/nutrition").body()
        } catch (e: Exception) {
            Log.d(
                "Recipe service get recipe error",
                e.toString(),
            )
            Nutrition()
        }
    }

    override suspend fun getRecipe(recipeId: String): RecipeDto? {
        return try {
            client.get("$BASE_URL/recipe/$recipeId").body()
        } catch (e: Exception) {
            Log.d(
                "Recipe service get recipe error",
                e.toString(),
            )
            null
        }
    }

}
