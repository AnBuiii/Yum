package com.anbui.yum.presentation.recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.data.remote.service.RecipeService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalFoundationApi::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeService: RecipeService,

    ) : YumViewModel() {

    val uiState = mutableStateOf(RecipeDetailUiState())

    fun getRecipe(recipeId: String) {
        viewModelScope.launch {
            try{
                uiState.value = uiState.value.copy(recipe = recipeService.getRecipe(recipeId)!!)
            } catch (e: Exception){
                uiState.value = uiState.value.copy(recipe = Recipe(
                    title = "asd"
                ))
            }
        }
    }

}
