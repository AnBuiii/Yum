package com.anbui.yum.presentation.feed.tabs

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.anbui.yum.common.component.PullRefreshIndicator
import com.anbui.yum.common.component.YumRecipeCard
import com.anbui.yum.common.component.pullRefresh
import com.anbui.yum.common.component.rememberPullRefreshState
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.feed.SuggestionCard

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

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = recipes::refresh,
    )

    val state = rememberLazyListState()
    Column {
//        item {

//        }
//        if (recipes.loadState.refresh is LoadState.Loading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//            )
//        }

        Box(
            modifier = Modifier.pullRefresh(pullRefreshState),
        ) {
            LazyColumn(
                state = state,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                itemsIndexed(
                    recipes,
                    key = { _, recipe ->
                        recipe.id
                    },
                ) { idx, recipe ->
                    if (idx == 0) {
                        SuggestionCard()
                    }
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

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }

        LaunchedEffect(recipes.loadState.refresh) {
            isRefreshing = recipes.loadState.refresh is LoadState.Loading

        }
    }


}
