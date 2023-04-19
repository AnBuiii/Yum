package com.anbui.yum.presentation.feed.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.anbui.yum.common.component.YumRecipeCard
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.feed.SuggestionCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JFYTab(
    modifier: Modifier = Modifier,
    recipes: LazyPagingItems<Recipe>,
    onTap: (String) -> Unit = {},
    onReload: () -> Unit,
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    val state = rememberLazyListState()
    Column {
//        item {
        SuggestionCard()
//        }
//        if (recipes.loadState.refresh is LoadState.Loading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//            )
//        }
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                recipes.refresh()
            },
        ) {
            LazyColumn(
                state = state,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(
                    recipes,
                    key = { it.id },
                ) { recipe ->
                    if (recipe != null) {
                        YumRecipeCard(
                            recipe = recipe,
                            onTap = { onTap(recipe.id) },
                        )
                    }
                }
//                item {
//                    if (recipes.loadState.append is LoadState.Loading) {
//                        CircularProgressIndicator()
//                    }
//                }

            }
        }

        LaunchedEffect(recipes.loadState.refresh) {
            isRefreshing = recipes.loadState.refresh is LoadState.Loading

        }
    }


}