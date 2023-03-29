package com.anbui.yum.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun YumTabRow(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    tabList: List<String>,
    textSize: TextUnit = 12.sp,
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
                        .padding(vertical = 22.dp),
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




@Composable
fun YumTabRowPair(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    tabList: List<Pair<String, String>>,
    textSize: TextUnit = 12.sp,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier.height(40.dp),
        // weight(2f)
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .padding(horizontal = 4.dp),
//                height = 3.dp,
            )

        },
        divider = {},
    ) {

        tabList.forEachIndexed { index, detail ->
            Tab(
                selected = index == selectedTab,
                onClick = { onTabChange(index) },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 1.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = detail.first,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = textSize,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                    Text(
                        text = detail.second,
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
