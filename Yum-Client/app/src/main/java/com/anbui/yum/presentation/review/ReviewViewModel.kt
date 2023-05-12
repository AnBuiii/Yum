package com.anbui.yum.presentation.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.mappers.toUserInfo
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.review.ReviewService
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.domain.model.Review
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val recipeService: RecipeService,
    private val reviewService: ReviewService,
    private val userInfoService: UserInfoService,
    private val yumDatabase: YumDatabase,
) : YumViewModel() {
    val uiState = mutableStateOf(ReviewUiState())

    fun init(recipeId: String) {
        viewModelScope.launch {
            val hm = yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId
            if (hm != null) {
                uiState.value = uiState.value.copy(
                    userInfo = userInfoService.getUserInfo(hm)!!.toUserInfo(),
                    recipe = recipeService.getRecipe(recipeId)!!.toRecipe(),
                )
            }

        }
    }

    fun onRatingChange(newValue: Float) {
        uiState.value = uiState.value.copy(rating = newValue)
    }

    fun onCommentChange(newValue: String) {
        uiState.value = uiState.value.copy(comment = newValue)
    }

    suspend fun onSubmit(): Boolean {
        return reviewService.submitReview(
            Review(
                message = uiState.value.comment,
                rating = uiState.value.rating.toInt(),
                userId = uiState.value.userInfo.userId,
                recipeId = uiState.value.recipe.id,
                timestamp = System.currentTimeMillis(),
            ),
        )
    }


}
