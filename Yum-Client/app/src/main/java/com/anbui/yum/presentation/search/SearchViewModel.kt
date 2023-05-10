package com.anbui.yum.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val ingredientService: IngredientService,
) : YumViewModel() {


    val uiState = mutableStateOf(SearchUiState())

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()



    private val _persons = MutableStateFlow(listOf<String>())
    var persons = searchText
        .debounce(100)
        .onEach { _isSearching.update { true } }

//        .transform {
//            ingredientService.search(searchText.value)
//        }
        .combine(_persons) { _, _ ->
//            delay(100)
            ingredientService.search(searchText.value)


        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        viewModelScope.launch {
            ingredientService.search("a")
        }
    }

    suspend fun search(){
        uiState.value = uiState.value.copy(
            searchedTexts = ingredientService.search(uiState.value.searchText),
        )
    }
    fun onSearchTextChangehm(newValue: String) {
        uiState.value = uiState.value.copy(
            searchText = newValue,
        )
    }

    fun onSearchTextFocusChange(newValue: Boolean) {
        uiState.value = uiState.value.copy(isSearchFocused = newValue)
    }

    fun onClearSearchText() {
        uiState.value = uiState.value.copy(searchText = "")
    }

    fun onSearchStatusChange(newValue: Boolean) {
        uiState.value = uiState.value.copy(isSearching = newValue)
    }


}
