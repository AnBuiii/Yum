package com.anbui.yum.presentation.feed

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anbui.yum.R
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.common.component.YumTabRow
import com.anbui.yum.presentation.feed.tabs.ExploreTab
import com.anbui.yum.presentation.feed.tabs.JFYTab
import com.anbui.yum.presentation.feed.tabs.ProTab
import com.anbui.yum.ui.theme.YumOrange
import kotlinx.coroutines.launch


val tabList = listOf("Just for you", "Explore", "Pro")
val cardString =
    listOf("Make a Meal Plan", "Learn from the Pros", "Browse Articles for Inspiration")

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    onRecipeTap: (String) -> Unit = {},
    onCollectionTab: (String) -> Unit = {},
    viewModel: FeedViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState

    val recipes = viewModel.recipePagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()



    YumSurface(
        modifier = modifier,
        color = Color.White
    ) {
        Column {

            FeedTopBar(
                selectedTab = pagerState.currentPage,
                onTabChange = {
                    coroutineScope.launch { pagerState.animateScrollToPage(it) }
                },
            )
//            JFYTab(
//                recipes = recipes,
//                onTap = { viewModel.onRecipeTap(onRecipeTap, it) },
//            )


            HorizontalPager(
                pageCount = 3,
                state = pagerState,
                verticalAlignment = Alignment.Top,

                ) { index ->
                when (index) {
                    0 -> JFYTab(
                        recipes = recipes,
                        onTap = { viewModel.onRecipeTap(onRecipeTap, it) }
                    ) { recipes.refresh() }

                    1 -> ExploreTab(
                        onCollectionTab = onCollectionTab
                    )
                    2 -> ProTab()
                }
            }


        }
    }

    LaunchedEffect(key1 = recipes.loadState) {
        if (recipes.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (recipes.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }
}

@Composable
fun SuggestionCard() {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        //                    .background(Color(232, 233, 235))
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(cardString) {
            Card(
                modifier = Modifier
                    .aspectRatio(2.4f)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.food_1),
                        contentDescription = "",
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            it,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }

}

@ExperimentalMaterial3Api
@Composable
private fun FeedTopBar(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp),
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 22.sp),
                color = YumOrange,
            )
        }

        YumTabRow(
            selectedTab = selectedTab,
            onTabChange = onTabChange,
            tabList = tabList,
            modifier = Modifier.weight(2f),
        )
    }
}
