package com.example.yum.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.common.component.YumSearchBar
import com.example.yum.common.component.YumDivider
import kotlinx.coroutines.delay


@ExperimentalMaterial3Api
@Composable
fun SearchRoute(
    onRecipeClick: (String) -> Unit,
) {
    SearchScreen(onRecipeClick = {}, modifier = Modifier)
}

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    val tabList = listOf("Just for you", "Explore", "Pro")
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        YumSearchBar(
            searchText = uiState.searchText,
            onQueryChange = viewModel::onSearchTextChange,
            searchFocused = uiState.isSearchFocused,
            onSearchFocusChange = viewModel::onSearchTextFocusChange,
            onClearQuery = viewModel::onClearSearchText,
            searching = uiState.isSearching
        )

        YumDivider()

        LaunchedEffect(uiState.searchText) {
            viewModel.onSearchStatusChange(true)
            delay(2000)
            viewModel.onSearchStatusChange(false)
        }
        TabRow(
            selectedTabIndex = uiState.tabState
        ) {
            tabList.mapIndexed { index, tab ->
                Tab(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 1.dp),
                    selected = uiState.tabState == index,
                    onClick = { viewModel.scrollToTab(index) }) {
                    Text(text = tab)
                }

            }
        }
    }
}

