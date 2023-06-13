package com.anbui.yum.presentation.category

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val recipeService: RecipeService,
) : YumViewModel() {
    val uiState = mutableStateOf(CategoryUiState())
    suspend fun init(name: String) {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                recipes = recipeService.getRecipeByCategory(name = name).map { it.toRecipe() },
            )
            Log.d("Category",
                uiState.value.recipes.toString()
            )
        }
    }
}
