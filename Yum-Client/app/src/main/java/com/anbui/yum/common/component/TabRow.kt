package com.anbui.yum.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen


@Composable
fun YumTabRow(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    tabList: List<String>,
    textSize: TextUnit = 12.sp,
    height: Dp = 22.dp,
    indicatorHeight: Dp = 3.dp
) {
    TabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier,
        // weight(2f)
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .padding(horizontal = 8.dp),
                color = YumGreen,
                height = indicatorHeight,
            )

        },
        containerColor = Color.White,
        divider = {},
    ) {

        tabList.forEachIndexed { index, s ->
            Tab(
                selected = index == selectedTab,
                onClick = { onTabChange(index) },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = height),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = s,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = textSize,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                }
            }
        }
    }
}




