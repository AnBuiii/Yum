package com.example.yum.screens.search

import com.example.yum.model.SearchCategoryCollection
import com.example.yum.model.searchCategoryCollections

data class SearchUiState(
    val tabState: Int = 0,
    val searchText: String = "",
    val isSearchFocused: Boolean = false,
    val isSearching: Boolean = false,

)