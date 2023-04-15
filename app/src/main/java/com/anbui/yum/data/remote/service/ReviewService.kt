package com.anbui.yum.data.remote.service

import com.anbui.yum.domain.model.Review

interface ReviewService{

    suspend fun getReview(id: String) : Review?
    suspend fun getReviewByRecipe(recipeId: String): List<Review>

}
