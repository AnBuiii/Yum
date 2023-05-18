package com.anbui.yum.presentation.cart.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.common.component.PullRefreshIndicator
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.common.component.pullRefresh
import com.anbui.yum.common.component.rememberPullRefreshState
import com.anbui.yum.common.component.rememberSwipeableState
import com.anbui.yum.domain.model.ShoppingItem
import com.anbui.yum.presentation.cart.components.CategoryHeaderItem
import com.anbui.yum.presentation.cart.components.CategoryItem
import com.anbui.yum.presentation.cart.components.YumIconButton
import com.anbui.yum.ui.theme.YumGreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShopTab(
    modifier: Modifier = Modifier,
    hmItems: List<ShoppingItem>,
    onCheck: (String, Boolean) -> Unit,
    onEdit: (String) -> Unit,
    onAddShoppingList: () -> Unit,
    onRefresh: () -> Unit,
    onRemove: (String) -> Unit,
) {


    val coroutineScope = rememberCoroutineScope()
    val groupedItems = hmItems.groupBy { it.categoriesName }
    val collapseStates = remember { mutableStateListOf<Boolean>() }
    var currentRevealed by remember { mutableStateOf("") }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(hmItems.size) {
        collapseStates.clear()
        collapseStates.addAll(List(groupedItems.size) { false })
    }

    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                currentRevealed = ""
            }
            .fillMaxSize(),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 16.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.clickable { onAddShoppingList() },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = R.drawable.outline_add_circle),
                    contentDescription = "",
                    tint = YumGreen,
                )
                Text(
                    "Add to Shopping List",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
            }

            Row(
                modifier = Modifier.clickable { },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                YumIconButton(
                    onClick = {},
                    text = "CATEGORY",
                    icon = Icons.Default.Menu,
                    modifier = Modifier,
                    iconSize = 22.dp,
                )
                YumDivider(
                    thickness = 24.dp,
                    modifier = Modifier.width(1.dp),
                )
                YumIconButton(
                    onClick = {},
                    text = "EDIT",
                    icon = Icons.Default.Edit,
                    modifier = Modifier,
                )
            }
        }
        YumDivider()
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = onRefresh,
        )
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn {
                groupedItems.toList().forEachIndexed { idx, (categories, items) ->
                    stickyHeader(key = categories) {
                        CategoryHeaderItem(
                            categoriesName = categories,
                            amount = items.size,
                            isCollapsed = false,
                            onTab = {
                                collapseStates[idx] = !collapseStates[idx]
                            },
                            //                        modifier = Modifier.animateItemPlacement()
                        )
                    }
                    if (collapseStates.getOrNull(idx) == false) {
                        items(
                            items,
                            key = { it.id },
                        ) { item ->
                            val swipeState = rememberSwipeableState(
                                initialValue = 0,
                                confirmStateChange = {
                                    currentRevealed = if (it == 0) {
                                        ""
                                    } else {
                                        item.id
                                    }
                                    true
                                },
                            )
                            CategoryItem(
                                shoppingItem = item,
                                onCheck = {
                                    onCheck(
                                        item.id,
                                        !item.isChecked,
                                    )
                                },
                                onEdit = {
                                    onEdit(item.id)
                                    currentRevealed = ""
                                },
                                onRemove = {
                                    onRemove(item.id)
                                },
                                swipeState = swipeState,
                            )
                            LaunchedEffect(
                                currentRevealed,
                            ) {
                                if (currentRevealed != item.id && swipeState.currentValue == 1) {
                                    swipeState.animateTo(0)
                                }
                            }
                        }

                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}
