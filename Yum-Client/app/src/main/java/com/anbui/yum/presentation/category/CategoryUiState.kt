package com.anbui.yum.presentation.category

import com.anbui.yum.domain.model.Recipe

data class CategoryUiState(
    val recipes: List<Recipe> = emptyList(),
)
