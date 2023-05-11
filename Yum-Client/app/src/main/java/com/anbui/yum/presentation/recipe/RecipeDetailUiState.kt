package com.anbui.yum.presentation.recipe


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import com.anbui.yum.domain.model.Ingredient
import com.anbui.yum.domain.model.Nutrition
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.domain.model.Review


@OptIn(ExperimentalFoundationApi::class)
data class RecipeDetailUiState(
    val recipe: Recipe = Recipe(),
    val review: List<Review> = listOf(),
    val currentNutrition: Nutrition = Nutrition(),
    val selectedTab: Int = 0,
    val pagerState: PagerState = PagerState(0,0f),
    val ingredients: List<Ingredient> = listOf()
)
