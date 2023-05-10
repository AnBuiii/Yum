package com.anbui.yum.presentation.search

data class SearchUiState(
    val searchText: String = "",
    val searchedTexts: List<String> = listOf(),
    val isSearchFocused: Boolean = false,
    val isSearching: Boolean = false,
)
