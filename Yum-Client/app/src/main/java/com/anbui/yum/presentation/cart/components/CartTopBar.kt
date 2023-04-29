package com.anbui.yum.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.common.component.YumTabRow
import com.anbui.yum.presentation.cart.cartTabList

@Composable
fun CartTopBar(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit = {},
    onMoreTap: () -> Unit = {},
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        YumTabRow(
            selectedTab = selectedTab,
            onTabChange = onTabChange,
            tabList = cartTabList,
            modifier = Modifier.width(250.dp),
            textSize = 21.sp,
            height = 6.dp,
            indicatorHeight = 4.dp
        )
        IconButton(onClick = onMoreTap) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.size(26.dp),
            )
        }

    }
}
