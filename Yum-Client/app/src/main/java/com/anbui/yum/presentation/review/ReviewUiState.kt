package com.anbui.yum.presentation.review

import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.domain.model.UserInfo

data class ReviewUiState(
    val userInfo: UserInfo = UserInfo(),
    val recipe: Recipe = Recipe(),
    val rating: Float = 1f,
    val comment: String = "",
)
