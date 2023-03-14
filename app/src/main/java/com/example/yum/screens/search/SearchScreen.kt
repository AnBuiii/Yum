package com.example.yum.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.common.component.YumDivider
import com.example.yum.common.component.YumSearchBar
import com.example.yum.common.component.YumSurface
import com.example.yum.common.component.YumVerticalGrid
import com.example.yum.model.SearchCategory
import com.example.yum.model.SearchCategoryCollection


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
//        Column(
//            modifier = Modifier
////                .verticalScroll(rememberScrollState())
////                .fillMaxSize()
//        ) {
//
//            Spacer(modifier = Modifier.height(16.dp))
//            YumSearchBar(
//                searchText = uiState.searchText,
//                onQueryChange = viewModel::onSearchTextChange,
//                searchFocused = uiState.isSearchFocused,
//                onSearchFocusChange = viewModel::onSearchTextFocusChange,
//                onClearQuery = viewModel::onClearSearchText,
//                searching = uiState.isSearching
//            )
//
//            YumDivider()
//
//            LaunchedEffect(uiState.searchText) {
//                viewModel.onSearchStatusChange(true)
//                delay(2000)
//                viewModel.onSearchStatusChange(false)
//            }
//            TabRow(
//                selectedTabIndex = uiState.tabState
//            ) {
//                tabList.mapIndexed { index, tab ->
//                    Tab(
//                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 1.dp),
//                        selected = uiState.tabState == index,
//                        onClick = { viewModel.scrollToTab(index) }) {
//                        Text(text = tab)
//                    }
//
//                }
//            }
//
//            SearchCategories(categories = viewModel.getSearchCategories())
//
//
//        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // top padding
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // search bar
            item {
                YumSearchBar(
                    searchText = uiState.searchText,
                    onQueryChange = viewModel::onSearchTextChange,
                    searchFocused = uiState.isSearchFocused,
                    onSearchFocusChange = viewModel::onSearchTextFocusChange,
                    onClearQuery = viewModel::onClearSearchText,
                    searching = uiState.isSearching
                )
            }

            // divider
            item {
                YumDivider()
            }

            items(viewModel.getSearchCategories()) { collection ->
//                LazyColumn{
//                    item{Text(collection.name)}
//                    items(collection.categories){
////                        LazyVerticalGrid(GridCells.Fixed(2)){
////
////                        }
//                    }
//                }

                Column {
                    Text(
                        text = collection.name,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    YumVerticalGrid {
                        collection.categories.forEach {
                            CategoryCard(
                                category = it,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

            }


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
            modifier = Modifier.padding(horizontal = 8.dp)
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
        LazyColumn {

        }

    }
}

@Composable
fun CategoryCard(
    category: SearchCategory,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .aspectRatio(0.9f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.6f)
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .aspectRatio(2.5f),

                ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        category.name,
//                        style = MaterialTheme.typography.bodyLarge
                        fontSize = 18.sp
                    )
                }
            }

        }
    }
}
