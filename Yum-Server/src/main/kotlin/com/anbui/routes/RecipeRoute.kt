package com.anbui.routes

import com.anbui.data.ingredient.IngredientDataSource
import com.anbui.data.recipe.Nutrition
import com.anbui.data.recipe.Recipe
import com.anbui.data.recipe.RecipeDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*


val caloriesMax = 2000
val fatCaloriesMax = 500
val proteinCalories = 600
val carbCaloriesMax = 900
val cholesterolMax = 300
val sodiumMax = 2300
val pottasiumMax = 3400
val calciumMax = 1000
val ironMax = 8
fun Route.recipeRoute(
    recipeDataSource: RecipeDataSource,
    ingredientDataSource: IngredientDataSource,
) {

    route("recipe") {
        // create
        post {
            val request = call.receive<Recipe>()
            try {
                val recipe = recipeDataSource.insertRecipe(request)
                call.respond(recipe)
            } catch (e: Exception) {
                application.log.error("Fail to insert new user info")
            }
        }
        get {
            try {
                val page = call.request.queryParameters["page"]?.toInt() ?: 1
                val pageCount = call.request.queryParameters["per_page"]?.toInt() ?: Int.MAX_VALUE
                val recipes = recipeDataSource.getRecipes(page, pageCount)
                call.respond(recipes)
            } catch (e: Exception) {
                application.log.error("Fail to get all user info")
            }
        }

        get("category"){
            val request = call.receive<String>()
            try{
                val recipes = recipeDataSource.getRecipeByCategory(request)
                call.respond(recipes)
            } catch(e: Exception){
                application.log.error(e)
            }
        }

        get("size") {
            val recipes = recipeDataSource.getRecipes()
            call.respond(recipes.size)
        }

        get("search") {
            val query = call.request.queryParameters["q"] ?: ""
            try {
                val recipe = recipeDataSource.search(query)
                print(recipe)
                call.respond(recipe)
            } catch (e: Exception) {
                application.log.error("Fail to search many recipe")
            }
        }

        route("{id}") {
            get {
                val id = call.parameters["id"]
                try {
                    val recipe = recipeDataSource.getRecipe(id!!)
                    call.respond(recipe!!)
                } catch (e: Exception) {
                    application.log.error("Fail to get user info")
                }
            }
            get("/nutrition") {
                val id = call.parameters["id"]
                try {
                    val recipe = recipeDataSource.getRecipe(id!!)
                    var nutrition = Nutrition()
                    recipe!!.ingredients?.map { detail ->
                        val ingredient = ingredientDataSource.getIngredient(detail.ingredientId)!!
                        println(ingredient.toString())
//                        val caloriesFromProtein =
//                            nutrition.caloriesFromProtein + ingredient.protein * detail.amount / recipe.servings

                        nutrition = nutrition
                            .copy(
                                caloriesFromProtein = nutrition.caloriesFromProtein + ingredient.protein * detail.amount / recipe.servings,
                                caloriesFromCarbs = nutrition.caloriesFromCarbs + ingredient.carb * detail.amount / recipe.servings,
                                caloriesFromFat = nutrition.caloriesFromProtein + ingredient.protein * detail.amount / recipe.servings,
                                cholesterol = nutrition.cholesterol + ingredient.cholesterol * detail.amount / recipe.servings,
                                sodium = nutrition.sodium + ingredient.sodium * detail.amount / recipe.servings,
                                potassium = nutrition.potassium + ingredient.potassium * detail.amount / recipe.servings,
                                calcium = nutrition.calcium + ingredient.calcium * detail.amount / recipe.servings,
                                iron = nutrition.iron + ingredient.iron * detail.amount / recipe.servings,
                                calories = nutrition.caloriesFromFat + nutrition.caloriesFromCarbs + nutrition.caloriesFromProtein,
                            )
                    }
                    call.respond(nutrition)
                } catch (e: Exception) {
                    application.log.error(e.toString())
                }
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
//                application.log.error("Fail to get user info")
//            }
//        }

    }

}
