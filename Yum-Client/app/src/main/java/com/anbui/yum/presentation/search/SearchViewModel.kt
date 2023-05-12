package com.anbui.yum.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SearchViewModel(
    private val recipeService: RecipeService
) : YumViewModel() {


    val uiState = mutableStateOf(SearchUiState())

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _persons = MutableStateFlow(listOf<Recipe>())
    @OptIn(FlowPreview::class)
    var persons = searchText
        .debounce(100)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { _, _ ->
            recipeService.search(searchText.value).map { it.toRecipe() }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value,
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}
