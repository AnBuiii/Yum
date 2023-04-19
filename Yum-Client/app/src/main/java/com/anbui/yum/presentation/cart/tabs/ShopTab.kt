package com.anbui.yum.presentation.cart.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.presentation.cart.components.CategoryHeaderItem
import com.anbui.yum.presentation.cart.components.CategoryItem
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
                    CategoryItem(
                        shoppingList = item,
                        onCheck = { /*TODO*/ },
                        onEdit = { /*TODO*/ },
                        onDelete = { }
                    )
                }
            }
        }
    }


}
