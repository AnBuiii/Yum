package com.anbui.yum.presentation.cart.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.presentation.cart.components.CategoryHeaderItem
import com.anbui.yum.presentation.cart.components.CategoryItem
import com.anbui.yum.presentation.cart.components.YumIconButton
import com.anbui.yum.ui.theme.YumGreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShopTab(
    modifier: Modifier = Modifier,
    hmItems: List<ShoppingList>,
    onCheck: (String) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    val groupedItems = hmItems.groupBy { it.categoriesName }
    val collapseStates = remember { mutableStateListOf(*Array(groupedItems.size) { false }) }


    Column {
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
                modifier = Modifier.clickable { },
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
                if (!collapseStates[idx]) {
                    items(
                        items,
                        key = { it.id },
                    ) { item ->
                        CategoryItem(
                            shoppingList = item,
                            onCheck = { /*TODO*/ },
                            onEdit = { /*TODO*/ },
                            onDelete = { },
                        )
                    }
                }
            }
        }
    }


}
