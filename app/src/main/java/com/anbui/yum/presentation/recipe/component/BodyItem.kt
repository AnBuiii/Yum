package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
//import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anbui.yum.domain.model.Nutrition
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.domain.model.Review
import com.anbui.yum.presentation.recipe.tabs.DirectionTab
import com.anbui.yum.presentation.recipe.tabs.IngredientTab
import com.anbui.yum.presentation.recipe.tabs.NutritionTab
import com.anbui.yum.presentation.recipe.tabs.OverviewTab
import com.anbui.yum.presentation.recipe.tabs.ReviewTab

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BodyItem(
    pagerState: PagerState,
    recipe: Recipe,
    nutrition: Nutrition,
    reviews: List<Review>,
//    state: BottomSheetScaffoldState

) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
//        Spacer(Modifier.height(TITLE_HEIGHT))
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
                3 -> NutritionTab(nutrition)
                4 -> ReviewTab(reviews)
            }


        }

    }
}
