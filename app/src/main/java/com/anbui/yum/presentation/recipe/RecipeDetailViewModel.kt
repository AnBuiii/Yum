package com.anbui.yum.presentation.recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.service.ReviewService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeService: RecipeService,
    private val reviewService: ReviewService,

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
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    recipe = Recipe(
                        title = "asd",
                    ),

                    )
            }
        }
    }


    fun getUser() {

    }

}
