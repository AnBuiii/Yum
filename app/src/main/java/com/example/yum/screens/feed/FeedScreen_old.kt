package com.example.yum.screens.feed
//
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.pager.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.*
//import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.*
//import androidx.compose.ui.graphics.*
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.imageResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringArrayResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.util.lerp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.yum.R
//import kotlin.math.absoluteValue
//import com.example.yum.R.string as AppText
//
//
//@ExperimentalFoundationApi
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FeedScreenOld(
//    onRecipeTap: () -> Unit,
//    modifier: Modifier = Modifier,
//    viewModel: FeedViewModel = hiltViewModel(),
//) {
//
//    val uiState by viewModel.uiState
//    val tabList = listOf("Newest food", "Best recipe", "Popular ingredientttttt")
//    val state = rememberLazyListState()
///*
//    val option = BitmapFactory.Options()
//    option.inPreferredConfig = Bitmap.Config.ARGB_8888
//    val bitmap = BitmapFactory.decodeResource(
//        LocalContext.current.resources,
//        R.drawable.food_1,
//        option
//    ).asImageBitmap()*/
//
//    //                val bg = Bitmap.createScaledBitmap(
////                    BitmapFactory.decodeResource(
////                        LocalContext.current.resources,
////                        R.drawable.food_1
////                    ), 300, 300, true
////                )
//
//    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.food_1)
//
//    Column(
////        state = state,
//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
//            .fillMaxSize()
//
//    ) {
////        item {
//        Spacer(modifier = Modifier.height(100.dp))
//        Text(
//            text = stringResource(id = AppText.app_name),
//            modifier = Modifier.padding(start = 16.dp, top = 32.dp),
//            style = MaterialTheme.typography.displayMedium,
//            color = Color.Black
//
//        )
////        }
//
//        // Quote
////        item {
//        Text(
//            text = stringArrayResource(id = R.array.quote)[0],
//            modifier = Modifier.padding(start = 16.dp)
//        )
////        }
//
//        //search bar + filter button
////        item {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(6.dp),
//            modifier = Modifier
//                .padding(16.dp)
//                .height(IntrinsicSize.Max),
//
//            ) {
//            OutlinedTextField(
//                value = uiState.searchText,
//                onValueChange = {},
//                leadingIcon = { Icon(imageVector = Icons.Default.Search, "") },
//                placeholder = { Text(text = stringResource(id = AppText.search_placeholder)) },
//                shape = RoundedCornerShape(10.dp),
//                modifier = Modifier.weight(1f)
//            )
//            OutlinedIconButton(
//                onClick = { },
//                shape = RoundedCornerShape(10.dp),
////                border = BorderStroke(
////                    width = 1.dp,
////                    color = Color.Black
////                ),
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .aspectRatio(1f)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Filter",
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//            OutlinedIconButton(
//                onClick = { },
//                shape = RoundedCornerShape(10.dp),
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .aspectRatio(1f)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Filter"
//                )
//            }
//        }
////        }
//
//        // tab
////        item {
//        val indicatorColor = MaterialTheme.colorScheme.primary
//        ScrollableTabRow(
//
//            modifier = Modifier.padding(bottom = 10.dp),
//            selectedTabIndex = uiState.tabState,
//            edgePadding = 0.dp,
//            indicator = { tabPositions ->
//                Canvas(
//                    modifier = Modifier
//                        .tabIndicatorOffset(tabPositions[uiState.tabState])
//                        .height(10.dp),
//                    onDraw = {
//                        drawCircle(
//                            color = indicatorColor,
//                            radius = 10f,
//                            center = Offset(
//                                tabPositions[uiState.tabState].width.toPx() / 2,
//                                0f
//                            )
//                        )
//                    }
//                )
//
//            },
//            divider = {}
//        ) {
//            tabList.forEachIndexed { index, tabTitle ->
//                Tab(
//                    selected = uiState.tabState == index,
//                    onClick = { viewModel.scrollToTab(index) },
//                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
//                ) {
//                    Text(
//                        tabTitle,
//                        maxLines = 1
//
//                    )
//                }
//            }
//        }
////        }
//
//        // card
////        item {
//        // pager
//        val pagerState = rememberPagerState()
//        val fling = PagerDefaults.flingBehavior(
//            state = pagerState,
//            pagerSnapDistance = PagerSnapDistance.atMost(10)
//        )
//
//
//        HorizontalPager(
//            modifier = Modifier.height(400.dp),
//            pageCount = 10,
//            state = pagerState,
//            pageSize = PageSize.Fixed(300.dp),
//            contentPadding = PaddingValues(horizontal = 56.dp),
//            pageSpacing = 8.dp,
////                flingBehavior = fling
//
//        ) { page ->
//            val pageOffset = (
//                    (pagerState.currentPage - page) + pagerState
//                        .currentPageOffsetFraction
//                    ).absoluteValue
//
//
//
//            Card(
//                Modifier
//                    .graphicsLayer {
//                        alpha = lerp(
//                            start = 0.5f,
//                            stop = 1f,
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        )
//                    }
//                    .size(
//                        lerp(
//                            start = 300f,
//                            stop = 350f,
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        ).dp
//                    ),
//                shape = RoundedCornerShape(30.dp)
//            ) {
//
//
//                Box(
//                    modifier = Modifier
//                        .background(Color.Gray.copy(alpha = 0.5f))
//                        .fillMaxSize()
//                ) {
//
//                    // food image
//                    Image(
//                        painter = painterResource(id = R.drawable.food_1),
//                        contentDescription = "",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Crop
//                    )
//
//                    // food image mask
///*                    Canvas(
//                        modifier = Modifier
////                            .alpha(0.5f)
//                            .fillMaxSize()
//                            .blur(20.dp)
//
//                    ) {
//                        val path = Path()
//
//                        path.addRoundRect(
//                            RoundRect(
//                                Rect(
//                                    Offset(16.dp.toPx(), 16.dp.toPx()),
//                                    Size(72.dp.toPx(), 72.dp.toPx())
//                                ),
//                                CornerRadius(10.dp.toPx())
//                            )
//                        )
//
//                        clipPath(path, clipOp = ClipOp.Intersect) {
//                            drawImage(
//                                imageBitmap,
//                                Offset(0f, 0f),
//                            )
//                        }
//                    }*/
//
//                    // bookmark icon
//
//                    FilledIconButton(
//                        onClick = {},
//                        shape = RoundedCornerShape(10.dp),
//                        colors = IconButtonDefaults.filledIconButtonColors(
//                            containerColor = Color.Black.copy(
//                                0.6f
//                            )
//                        ),
//                        modifier = Modifier
//                            .size(88.dp)
//                            .align(Alignment.TopStart)
//                            .padding(16.dp)
//                    ) {
//                        Icon(
//
//                            painterResource(id = R.drawable.bookmark_unfilled),
//                            contentDescription = "",
//                            modifier.size(32.dp),
//                            tint = Color.White
//                        )
//                    }
//
//                    // food info
//                    Box(
//                        modifier = Modifier
//                            .align(Alignment.BottomStart)
//                            .height(100.dp)
//                            .fillMaxWidth()
//                            .padding()
//                    ) {
//                        Card(
//                            shape = RoundedCornerShape(30.dp),
//                            colors = CardDefaults.cardColors(
//                                containerColor = Color.Black.copy(alpha = 0.6f)
//                            ),
//                            modifier = Modifier
//                                .align(Alignment.BottomCenter)
//                                .fillMaxWidth()
//                                .height(80.dp)
//
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//                            ) {
//                                // rate + name
//                                Column(
//                                    modifier = Modifier
//                                        .fillMaxHeight()
//                                        .align(Alignment.CenterStart),
//                                    horizontalAlignment = Alignment.Start,
//                                    verticalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    // rating
//                                    Row {
//                                        for (i in 1..5) {
//                                            Icon(
//                                                painter = painterResource(id = R.drawable.round_star_filled),
//                                                contentDescription = "",
//                                                tint = Color.Black
//                                            )
//                                        }
//                                    }
//                                    // name
//                                    Text(
//                                        text = "Neapolitan pizza",
//                                        fontWeight = FontWeight.SemiBold
//                                    )
//                                }
//
//
//                            }
//                        }
//                        Card(
//                            modifier = Modifier
//                                .align(Alignment.TopEnd)
//                                .size(56.dp)
//                                .offset((-16).dp, 0.dp),
//                            colors = CardDefaults.cardColors(
//                                containerColor = Color.Black
//                            ),
//                        ) {
//                            Column(
//                                modifier = Modifier.fillMaxSize(),
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.Center
//                            ) {
//                                Text("Yum")
//                                Text("142")
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
////        }
//
////        item {
//        Spacer(modifier = Modifier.height(500.dp))
////        }
//    }
//}
//
//
