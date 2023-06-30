package com.anbui.plugins

import com.anbui.data.ingredient.IngredientDataSource
import com.anbui.data.recipe.RecipeDataSource
import com.anbui.data.review.ReviewDataSource
import com.anbui.data.user.UserDataSource
import com.anbui.data.user_info.UserInfoDataSource
import com.anbui.routes.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting(

) {
    val userDataSource by inject<UserDataSource>()
    val userInfoDataSource by inject<UserInfoDataSource>()
    val recipeDataSource by inject<RecipeDataSource>()
    val ingredientDataSource by inject<IngredientDataSource>()
    val reviewDataSource by inject<ReviewDataSource>()


    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // auth route
        userRoute(userDataSource, userInfoDataSource)
        userInfoRoute(userInfoDataSource)
        recipeRoute(recipeDataSource, ingredientDataSource)
        ingredientRoute(ingredientDataSource)
        reviewRoute(reviewDataSource)
        shoppingRoute()
        collectionRoute()

    }
}

