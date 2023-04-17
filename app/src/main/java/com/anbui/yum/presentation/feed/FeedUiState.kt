package com.anbui.yum.presentation.feed

import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.domain.model.Recipe

data class FeedUiState(
    val tabState: Int = 0,
    val searchText: String = "",
    val recipes: List<Recipe> = listOf(),
//    val cardPager
)
