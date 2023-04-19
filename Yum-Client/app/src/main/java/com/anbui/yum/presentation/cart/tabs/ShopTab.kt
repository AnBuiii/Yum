package com.anbui.yum.presentation.cart.tabs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.anbui.yum.common.component.FractionalThreshold
import com.anbui.yum.common.component.rememberSwipeableState
import com.anbui.yum.common.component.swipeable
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.presentation.cart.components.CategoryHeaderItem
import com.anbui.yum.ui.theme.YumGreen
import kotlin.math.roundToInt


@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,

    )
@Composable
fun ShopTab(
    modifier: Modifier = Modifier,
    hmItems: List<ShoppingList>,
    onCheck: (String) -> Unit,
) {


    val groupedItems = hmItems.groupBy { it.categoriesName }
    val collapseStates = remember { mutableStateListOf(*Array(groupedItems.size) { false }) }

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.clickable { },
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.AddCircle,
                    contentDescription = "",
                    tint = YumGreen,
                )
                Text("Add to Shopping List", fontWeight = FontWeight.SemiBold)
            }
        }
        Divider()

        LazyColumn {


            groupedItems.toList().forEachIndexed { idx, (categories, items) ->
                stickyHeader(key = categories) {
                    CategoryHeaderItem(
                        categoriesName = categories,
                        amount = items.size,
                        isCollapsed = collapseStates[idx],
                        onTab = {
                            collapseStates[idx] = !collapseStates[idx]
                        },
//                        modifier = Modifier.animateItemPlacement()
                    )
                }

                items(items, key = { it.id }) { item ->
                    AnimatedVisibility(visible = collapseStates[idx]) {
                        val dismissState = rememberDismissState(
                            positionalThreshold = { 0f },
                        )
//                        SwipeToDismiss(
//                            state = dismissState,
//                            background = {
//                                val color by animateColorAsState(
//                                    when (dismissState.targetValue) {
////                                        Default -> Color.LightGray
//                                        DismissValue.DismissedToEnd -> Color.Green
//                                        DismissValue.DismissedToStart -> Color.Red
//                                        else -> Color.LightGray
//                                    }
//                                )
//                                Box(Modifier.fillMaxSize().background(color))
//                            },
//                            dismissContent = {
//                                Card {
//                                    ListItem(
//                                        headlineContent = {
//                                            Text("Cupcake")
//                                        },
//                                        supportingContent = { Text("Swipe me left or right!") }
//                                    )
//                                    Divider()
//                                }
//                            },
//                        )

//                        SwipeToDismiss(
//                            state = rememberDismissState(
//                                positionalThreshold = {0.3f}
//                            ),
//                            dismissContent = {
//                                ListItem(
//                                    headlineContent = { Text(item.foodName) },
//                                    leadingContent = {
//                                        IconButton(onClick = { /*TODO*/ }) {
//                                            Icon(
//                                                painter = painterResource(id = R.drawable.unchecked),
//                                                contentDescription = "",
//                                                tint = YumGreen,
//                                            )
//                                        }
//                                    },
//                                    supportingContent = { Text(item.recipeName) },
//                                    modifier = Modifier
////                                .animateItemPlacement()
//                                        .clickable {
//                                            onCheck(item.id)
//                                        },
//                                )
//                            },
//                            directions = setOf(DismissDirection.EndToStart),
//                            background = {
//                                Box(
//                                    modifier = Modifier
//                                        .height(20.dp)
//                                        .width(30.dp)
//                                        .background(Color.Red),
//                                ) {}
//                            },
//                        )

                        val directions: Set<DismissDirection> = setOf(
                            DismissDirection.EndToStart,
                            DismissDirection.StartToEnd,
                        )
                        val width = 200.dp
                        val squareSize = 100.dp

                        val swipeableState = rememberSwipeableState(0)
                        val sizePx = with(LocalDensity.current) { squareSize.toPx() }
                        val anchors =
                            mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

                        Box(
                            modifier = Modifier
                                .width(width)
                                .swipeable(
                                    state = swipeableState,
                                    anchors = anchors,
                                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                                    orientation = Orientation.Horizontal,
                                )
                                .background(Color.LightGray),
                        ) {
                            Box(
                                Modifier
                                    .offset {
                                        IntOffset(
                                            swipeableState.offset.value.roundToInt(),
                                            0,
                                        )
                                    }
                                    .size(squareSize)
                                    .background(Color.DarkGray),
                            )
                        }


                    }

                }
            }
        }
    }


}
