package com.example.yum.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YumTabRow(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    tabList: List<String>
) {
    TabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier,
        // weight(2f)
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .padding(horizontal = 4.dp),
                height = 3.dp,
            )

        },
        divider = {}
    ) {
        tabList.forEachIndexed { index, s ->
            Tab(
                selected = index == selectedTab,
                onClick = { onTabChange(index) },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 22.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = s,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}