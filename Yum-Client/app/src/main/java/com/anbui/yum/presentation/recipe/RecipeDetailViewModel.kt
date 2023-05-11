package com.anbui.yum.presentation.recipe

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.mappers.toIngredient
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.review.ReviewService
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class RecipeDetailViewModel(
    private val recipeService: RecipeService,
    private val reviewService: ReviewService,
    private val ingredientService: IngredientService,
) : YumViewModel() {

    val uiState = mutableStateOf(RecipeDetailUiState())

    fun getRecipe(recipeId: String) {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(
                    recipe = recipeService.getRecipe(recipeId)!!.toRecipe(),
                    currentNutrition = recipeService.getNutrition(recipeId),
                    review = reviewService.getReviewByRecipe(recipeId),
                )
                uiState.value.recipe.ingredients?.forEach {
                    uiState.value = uiState.value.copy(
                        ingredients = uiState.value.ingredients + ingredientService.getIngredientById(it.ingredientId)
                            .toIngredient(),
                    )
                }
                Log.d("Recipe", uiState.value.ingredients.toString())
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    recipe = Recipe(
                        title = "asd",
                    ),
                )
            }
        }
    }

    fun getIngredientNameById(id: String): String {
        var name = "b"
        viewModelScope.launch {
            name = try {

                val a = ingredientService.getIngredientById(id).name
                Log.d(
                    "RecipeDetailViewModel",
                    a,
                )
                a
            } catch (e: Exception) {
                Log.d(
                    "RecipeDetailViewModel",
                    "ingredient name",
                )
                "a"
            }
            name = "2"
        }

        return name
    }


    fun getUser() {

    }

}
