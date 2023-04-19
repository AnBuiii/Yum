package com.anbui.data.review;

import org.litote.kmongo.coroutine.CoroutineDatabase;
import org.litote.kmongo.eq

class ReviewDataSourceImpl(db: CoroutineDatabase) : ReviewDataSource{

    private val recipes = db.getCollection<Review>()
    override suspend fun getAllReview(): List<Review> {
        return recipes.find().toList()
    }

    override suspend fun getReview(id: String): Review? {
        return recipes.findOne(Review::id eq id)
    }

    override suspend fun getReviewByRecipe(recipeId: String): List<Review> {
        return recipes.find(Review::recipeId eq recipeId).toList()
    }

    override suspend fun insertReview(recipe: Review): Boolean {
        return recipes.insertOne(recipe).wasAcknowledged()
    }

}
