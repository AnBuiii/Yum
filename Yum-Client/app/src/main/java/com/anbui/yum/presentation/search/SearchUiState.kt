package com.anbui.yum.presentation.search

data class SearchUiState(
    val tabState: Int = 0,
    val searchText: String = "",
    val isSearchFocused: Boolean = false,
    val isSearching: Boolean = false,

)