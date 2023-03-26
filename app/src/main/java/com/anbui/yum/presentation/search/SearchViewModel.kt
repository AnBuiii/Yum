package com.anbui.yum.presentation.search

import androidx.compose.runtime.mutableStateOf

import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(

) : YumViewModel() {

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



}
