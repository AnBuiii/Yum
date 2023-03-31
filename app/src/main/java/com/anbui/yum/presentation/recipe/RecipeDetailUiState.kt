package com.anbui.yum.presentation.recipe


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import com.anbui.yum.data.model.Recipe


@OptIn(ExperimentalFoundationApi::class)
data class RecipeDetailUiState(
    val recipe: Recipe = Recipe(),
    val selectedTab: Int = 0,
    val pagerState: PagerState = PagerState(0,0f)
)
