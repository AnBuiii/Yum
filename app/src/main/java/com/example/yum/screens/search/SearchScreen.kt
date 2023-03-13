package com.example.yum.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.common.component.YumDivider
import com.example.yum.common.component.YumSearchBar
import com.example.yum.common.component.YumSurface
import com.example.yum.model.SearchCategory
import com.example.yum.model.SearchCategoryCollection
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
    YumSurface {
        Column(
            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .fillMaxSize()
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

            SearchCategories(categories = viewModel.getSearchCategories())


        }
    }
}


@Composable
fun SearchCategories(
    categories: List<SearchCategoryCollection>,
) {
    LazyColumn(
//        state = rememberLazyListState()
    ) {
        itemsIndexed(categories) { index, collection ->
            SearchCategoryCollectionCard(
                index = index,
                collection = collection,
            )
        }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
fun SearchCategoryCollectionCard(
    collection: SearchCategoryCollection,
    index: Int,
    modifier: Modifier = Modifier,
) {
    Column(
//        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = collection.name,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
//        LazyVerticalGrid(
////            state = rememberLazyGridState(),
//            columns = GridCells.Fixed(2),
//            verticalArrangement = Arrangement.spacedBy(4.dp),
//            horizontalArrangement = Arrangement.spacedBy(4.dp),
//            modifier = Modifier
////                .fillMaxWidth()
////                .wrapContentHeight()
//        ) {
////            item { Text("asd") }
////            items(collection.categories) { category ->
////                CategoryCard(category = category)
////            }
//
//        }
//        VerticalGrid {
//            collection.categories.forEach{category->
//                CategoryCard(category = category)
//            }
//        }
        LazyColumn{

        }

    }
}

@Composable
fun CategoryCard(
    category: SearchCategory,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .aspectRatio(1.4f)
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}
