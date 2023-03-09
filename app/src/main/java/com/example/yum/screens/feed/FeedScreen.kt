package com.example.yum.screens.feed


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.R
import com.example.yum.common.snackbar.SnackbarManager
import kotlin.math.absoluteValue
import com.example.yum.R.string as AppText


@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onRecipeTap: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    val tabList = listOf("Newest food", "Best recipe", "Popular ingredientttttt")
    val nestedScroll = rememberNestedScrollInteropConnection()
    val state = rememberLazyListState()
    val coroutineScope1 = rememberCoroutineScope()
    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxSize()
//            .nestedScroll(nestedScroll)
    ) {
        item {
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = stringResource(id = AppText.app_name),
                modifier = Modifier.padding(start = 16.dp, top = 32.dp),
                style = MaterialTheme.typography.displayMedium
            )
        }

        // Quote
        item {
            Text(
                text = stringArrayResource(id = R.array.quote)[0],
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        //search bar + filter button
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(IntrinsicSize.Max),

                ) {
                OutlinedTextField(
                    value = uiState.searchText,
                    onValueChange = {},
                    leadingIcon = { Icon(imageVector = Icons.Default.Search, "") },
                    placeholder = { Text(text = stringResource(id = AppText.search_placeholder)) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                )
                OutlinedIconButton(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Filter",
                        modifier = Modifier.size(24.dp)
                    )
                }
                OutlinedIconButton(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Filter"
                    )
                }
            }
        }

        // tab
        item {
            ScrollableTabRow(
                modifier = Modifier.padding(bottom = 10.dp),
                selectedTabIndex = uiState.tabState,
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    Canvas(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[uiState.tabState])
                            .height(10.dp),
                        onDraw = {
                            drawCircle(
                                color = Color.Black,
                                radius = 10f,
                                center = Offset(
                                    tabPositions[uiState.tabState].width.toPx() / 2,
                                    0f
                                )
                            )
                        }
                    )

                },
                divider = {}
            ) {
                tabList.forEachIndexed { index, tabTitle ->
                    Tab(
                        selected = uiState.tabState == index,
                        onClick = { viewModel.scrollToTab(index) },
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
                    ) {
                        Text(
                            tabTitle,
                            maxLines = 1

                        )
                    }
                }
            }
        }

        // card
        item {
            // pager
            val pagerState = rememberPagerState()
//            val fling = PagerDefaults.flingBehavior(
//                state = pagerState,
//                pagerSnapDistance = PagerSnapDistance.atMost(10)
//            )


            HorizontalPager(
                modifier = Modifier.height(400.dp),
                pageCount = 10,
                state = pagerState,
                pageSize = PageSize.Fixed(300.dp),
                contentPadding = PaddingValues(horizontal = 56.dp),
                pageSpacing = 8.dp,
//                flingBehavior = fling

            ) { page ->
                val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                Card(
                    Modifier
                        .graphicsLayer {
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .size(
                            lerp(
                                start = 300f,
                                stop = 350f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).dp
                        )
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                onClick = { SnackbarManager.showMessage(R.string.password_match_error) }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.food_1),
                            contentDescription = "",
                            modifier = Modifier.fillParentMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp)
                                .size(52.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .alpha(1f)
                                    .blur(
                                        radius = 2.dp,
                                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                                    )
                                    .background(
                                        Color.Transparent,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            )
                            FilledIconButton(
                                modifier = Modifier.fillMaxSize(),
                                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent),
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.bookmark_unfilled),
                                    contentDescription = "Filter",
                                    modifier = Modifier.size(32.dp),
                                    tint = Color.Red
                                )
                            }
                        }

                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(500.dp))
        }
    }
}

