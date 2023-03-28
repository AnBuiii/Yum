package com.anbui.yum.presentation.feed

//import com.example.yum.datastore.DataStoreRepository
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.RECIPE_DETAIL_SCREEN
import com.anbui.yum.RECIPE_ID
import com.anbui.yum.data.remote.service.RecipeService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val recipeService: RecipeService,
) : YumViewModel() {

    val uiState = mutableStateOf(FeedUiState())

    init {
        getFeedRecipes()
    }
    private fun getFeedRecipes() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(recipes = recipeService.getAllRecipe())
            Log.d("Recipe", uiState.value.recipes.toString())
        }
    }

    fun onRecipeTap(openScreen: (String) -> Unit, recipeId: String){
        openScreen("$RECIPE_DETAIL_SCREEN/$recipeId")
    }

    private val tabState
        get() = uiState.value.tabState

    fun scrollToPage(a: Int) {
//        uiState.value.tabState.
    }


    private val searchText
        get() = uiState.value.searchText

    fun scrollToTab(index: Int) {
        uiState.value = uiState.value.copy(tabState = index)
    }


    fun onSearchTextChange(newValue: String) {
        uiState.value = uiState.value.copy(searchText = newValue)
    }

}
