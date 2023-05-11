package com.anbui.yum.presentation.recipe

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.mappers.toIngredient
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.mappers.toUserInfo
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.review.ReviewService
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class RecipeDetailViewModel(
    private val recipeService: RecipeService,
    private val reviewService: ReviewService,
    private val ingredientService: IngredientService,
    private val userInfoService: UserInfoService
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

    suspend fun getIngredientNameById(id: String): String {
        return try {
            ingredientService.getIngredientById(id).name
        } catch (e: Exception){
            "unknown"
        }
    }

    suspend fun getUserInfoById(id: String): UserInfo{
        return try{
            userInfoService.getUserInfo(id)?.toUserInfo() ?: UserInfo()
        } catch (e: Exception){
            UserInfo()
        }
    }



    fun getUser() {

    }

}
