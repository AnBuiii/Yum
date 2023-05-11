package com.anbui.yum.presentation.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.mappers.toRecipe
import com.anbui.yum.data.mappers.toUserInfo
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.review.ReviewService
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val recipeService: RecipeService,
    private val reviewService: ReviewService,
    private val ingredientService: IngredientService,
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

    fun onRatingChange(newValue: Int) {
        uiState.value = uiState.value.copy(rating = newValue)
    }

    fun onCommentChange(newValue: String) {
        uiState.value = uiState.value.copy(comment = newValue)
    }

}
