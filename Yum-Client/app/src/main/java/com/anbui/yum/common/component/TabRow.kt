package com.anbui.yum.common.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.ui.theme.YumGreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun YumTabRow(
    modifier: Modifier = Modifier,
    tabList: List<String>,
    textSize: TextUnit = 12.sp,
    height: Dp = 22.dp,
    indicatorHeight: Dp = 3.dp,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier,
        // weight(2f)
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState,tabPositions)
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
                selected = index == pagerState.currentPage,
                onClick = {
//                    onTabChange(index)
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
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




