package com.anbui.yum.presentation.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.remote.service.RecipeService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeService: RecipeService

) : YumViewModel() {

    val uiState = mutableStateOf(RecipeDetailUiState())

    fun getRecipe(recipeId: String) {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(recipe = recipeService.getRecipe(recipeId)!!)
        }
    }

    fun onChangeTab(value: Int){
        uiState.value = uiState.value.copy(selectedTab = value)
    }
}
