package com.anbui.routes


import com.anbui.data.review.ReviewDataSource
import com.anbui.data.review.Review
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.reviewRoute(
    reviewDataSource: ReviewDataSource,
) {

    route("review") {
        // create
        post {
            val request = call.receive<Review>()
            try {
                val review = reviewDataSource.insertReview(request)
                call.respond(review)
            } catch (e: Exception) {
                application.log.error("Fail to insert new review")
            }
        }
        get {
            try {
                val reviews = reviewDataSource.getAllReview()
                call.respond(reviews)
            } catch (e: Exception) {
                application.log.error("Fail to get all review")
            }
        }
//         read
        get("{id}") {
            val id = call.parameters["id"]
            try {
                val review = reviewDataSource.getReview(id!!)
                call.respond(review!!)
            } catch (e: Exception) {
                application.log.error("Fail to get review")
            }
        }
        get("recipe/{id}") {
            val id = call.parameters["id"]
            try {
                val reviews = reviewDataSource.getReviewByRecipe(id!!)
                call.respond(reviews)
            } catch (e: Exception) {
                application.log.error("Fail to get review")
            }
        }
//
//        //update
//        put("{id}"){
//            val id = call.parameters["id"]
//            val request = call.receive<UserInfo>()
//            try {
//                val userInfo = userInfoDataSource.updateUserInfoById(id!!, request)
//                call.respond(userInfo)
//            } catch (e: Exception) {
//                application.log.error("Fail to get review")
//            }
//        }

    }

}
