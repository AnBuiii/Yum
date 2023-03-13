package com.example.yum.screens.search

import androidx.compose.runtime.mutableStateOf
import com.example.yum.model.SearchCategoryCollection
import com.example.yum.model.searchCategoryCollections
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    logService: LogService,
) : YumViewModel(logService) {

    val uiState = mutableStateOf(SearchUiState())

    fun onSearchTextChange(newValue: String) {
        uiState.value = uiState.value.copy(searchText = newValue)
    }

    fun scrollToTab(index: Int) {
        uiState.value = uiState.value.copy(tabState = index)
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

    fun getSearchCategories(): List<SearchCategoryCollection> = searchCategoryCollections

}
