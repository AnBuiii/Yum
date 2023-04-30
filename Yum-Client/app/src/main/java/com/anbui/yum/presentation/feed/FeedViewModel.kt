package com.anbui.yum.presentation.feed

//import com.example.yum.datastore.DataStoreRepository
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.anbui.yum.RECIPE_DETAIL_SCREEN
import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.remote.recipe.RecipeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val recipeService: RecipeService,
    private val pager: Pager<Int, RecipeEntity>,
) : ViewModel() {

    val uiState = mutableStateOf(FeedUiState())

    val recipePagingFlow = pager
        .flow
        .map {pagingData ->
            pagingData.map {
                it.toRecipe()
            }
        }
        .cachedIn(
            viewModelScope,
        )




    fun onRecipeTap(openScreen: (String) -> Unit, recipeId: String) {
        openScreen("$RECIPE_DETAIL_SCREEN/$recipeId")
    }


    private val tabState
        get() = uiState.value.tabState


    private val searchText
        get() = uiState.value.searchText


    fun onSearchTextChange(newValue: String) {
        uiState.value = uiState.value.copy(searchText = newValue)
    }

}
