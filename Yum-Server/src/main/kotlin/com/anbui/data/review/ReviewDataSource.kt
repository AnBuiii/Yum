package com.anbui.data.review

interface ReviewDataSource {
    suspend fun getAllReview(): List<Review>

    suspend fun insertReview(recipe: Review): Boolean

    suspend fun getReview(id: String): Review?

    suspend fun getReviewByRecipe(recipeId: String): List<Review>
}
