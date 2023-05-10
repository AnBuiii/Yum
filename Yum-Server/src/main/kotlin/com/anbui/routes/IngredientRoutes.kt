package com.anbui.routes


import com.anbui.data.ingredient.IngredientDataSource
import com.anbui.data.ingredient.Ingredient
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ingredientRoute(
    ingredientDataSource: IngredientDataSource,
) {

    route("ingredient") {
        // create
        post {
            val request = call.receive<Ingredient>()
            try {
                val ingredient = ingredientDataSource.insertIngredient(request)
                call.respond(ingredient)
            } catch (e: Exception) {
                application.log.error("Fail to insert new ingredient")
            }
        }

        get {
            try {
                val ingredients = ingredientDataSource.getAllIngredient()
                call.respond(ingredients)
            } catch (e: Exception) {
                application.log.error("Fail to get all ingredient")
            }
        }

        route("{id}") {
            get {
                val id = call.parameters["id"]
                try {
                    val ingredient = ingredientDataSource.getIngredient(id!!)
                    call.respond(ingredient!!)
                } catch (e: Exception) {
                    application.log.error("Fail to get ingredient")
                }
            }

        }

        post("/many") {
            val request = call.receive<List<Ingredient>>()
            try {
                val ingredient = ingredientDataSource.insertIngredients(request)
                call.respond(ingredient)
            } catch (e: Exception) {
                application.log.error("Fail to insert many ingredient")
            }
        }

        get("search") {
            val query = call.request.queryParameters["q"] ?: ""
            try {
                val ingredients = ingredientDataSource.search(query)
                print(ingredients)
                call.respond(ingredients)
            } catch (e: Exception) {
                application.log.error("Fail to search many ingredient")
            }
        }

//         read
//        get("{id}") {
//
//        }
//
//        //update
//        put("{id}"){
//            val id = call.parameters["id"]
//            val request = call.receive<UserInfo>()
//            try {
//                val userInfo = userInfoDataSource.updateUserInfoById(id!!, request)
//                call.respond(userInfo)
//            } catch (e: Exception) {
//                application.log.error("Fail to get ingredient")
//            }
//        }

    }

}
