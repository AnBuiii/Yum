package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.presentation.recipe.TITLE_HEIGHT
import com.anbui.yum.presentation.recipe.tabs.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyItem(
    pagerState: PagerState,
    recipe: Recipe,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
        Spacer(Modifier.height(TITLE_HEIGHT))
        HorizontalPager(
            pageCount = 5,
            state = pagerState,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.Top,

            ) { index ->
            when (index) {
                0 -> OverviewTab(recipe)
                1 -> IngredientTab(recipe)
                2 -> DirectionTab(recipe)
                3 -> NutritionTab(recipe)
                4 -> ReviewTab(recipe)
            }


        }

    }
}
