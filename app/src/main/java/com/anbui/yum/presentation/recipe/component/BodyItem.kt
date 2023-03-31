package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anbui.yum.presentation.recipe.IMAGE_HEIGHT
import com.anbui.yum.presentation.recipe.TITLE_HEIGHT
import com.anbui.yum.presentation.recipe.tabs.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyItem(
    scrollState: ScrollState,
    pagerState: PagerState,

    ) {
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth(),
//        modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection())

        ) {
        Spacer(Modifier.height(IMAGE_HEIGHT + TITLE_HEIGHT))
        HorizontalPager(
            pageCount = 5,
            state = pagerState,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.Top,

            ) { index ->
            when (index) {
                0 -> OverviewTab()
                1 -> IngredientTab()
                2 -> DirectionTab()
                3 -> NutritionTab()
                4 -> ReviewTab()
            }


        }

    }
}
