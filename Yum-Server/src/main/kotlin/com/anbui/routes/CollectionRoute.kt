package com.anbui.routes

import com.anbui.data.collection.Collection
import com.anbui.data.collection.CollectionDataSource
import com.anbui.data.collection.CollectionSend
import com.anbui.data.recipe.Recipe
import com.anbui.data.recipe.RecipeDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.collectionRoute() {
    val collectionDataSource by inject<CollectionDataSource>()
    val recipeDataSource by inject<RecipeDataSource>()
    route("collection") {
        post {
            val request = call.receive<Collection>()
            try {
                val respond = collectionDataSource.addCollection(request)
                call.respond(respond)
            } catch (e: Exception) {
                application.log.error("Fail to insert new collection")
            }
        }
        post("{id}") {
            val collectionId = call.parameters["id"]
            val recipeId = call.receive<String>()
            try {
                val respond = collectionDataSource.addRecipeToCollection(collectionId!!, recipeId)
                call.respond(respond)
            } catch (e: Exception) {
                application.log.error("Fail to insert new collection")
            }
        }

        get("{id}") {
            val id = call.parameters["id"]
            try {
                val collection = collectionDataSource.getCollection(id!!)!!
                val collectionRespond = CollectionSend(
                    id = collection.id,
                    title = collection.title,
                    userId = collection.userId,
                    recipes = collection.recipes.map { recipeId ->
                        recipeDataSource.getRecipe(recipeId)!!
                    },
                )
                call.respond(collectionRespond)
            } catch (e: Exception) {
                application.log.error("Fail to get collection $e")
            }
        }
        get("user/{id}") {
            val userId = call.parameters["id"]
            try {
                val collection = collectionDataSource.getCollectionsByUserId(userId!!)
                val collectionResponds = collection.map {
                    CollectionSend(
                        id = it.id,
                        title = it.title,
                        userId = it.userId,
                        recipes = it.recipes.map { recipeId ->
                            recipeDataSource.getRecipe(recipeId)!!
                        },
                    )
                }
                call.respond(collectionResponds)
            } catch (e: Exception) {
                application.log.error("Fail to get collection $e")
            }
        }
    }
}
