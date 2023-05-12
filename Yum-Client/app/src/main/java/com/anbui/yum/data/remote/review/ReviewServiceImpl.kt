package com.anbui.yum.data.remote.review

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import com.anbui.yum.domain.model.Review
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ReviewServiceImpl(
    private val client: HttpClient,
) : ReviewService {
    override suspend fun getReview(id: String): Review? {
        return try {
            client.get("$BASE_URL/review/$id").body()
        } catch (e: Exception) {
            Log.d(
                "Review service get recipe error",
                e.toString(),
            )
            null
        }
    }

    override suspend fun getReviewByRecipe(recipeId: String): List<Review> {
        return try {
            client.get("$BASE_URL/review/recipe/$recipeId").body()
        } catch (e: Exception) {
            Log.d(
                "Review service get recipe error",
                e.toString(),
            )
            listOf()
        }

    }

    override suspend fun submitReview(review: Review): Boolean {
        return try {
            client.post("$BASE_URL/review") {
                contentType(ContentType.Application.Json)
                setBody(review)
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Submit review fail",
                e.toString(),
            )
            false
        }
    }

}
