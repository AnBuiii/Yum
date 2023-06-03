package com.anbui.routes

import com.anbui.data.shoping_list.ShoppingItem
import com.anbui.data.shoping_list.ShoppingItemDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.shoppingRoute() {
    val shoppingItemDataSource by inject<ShoppingItemDataSource>()

    route("shopping") {
        post {
            val request = call.receive<ShoppingItem>()
            try {
//                val shoppingList = shoppingItemDataSource.getShoppingListByUserId(request.userId)
//                val a = shoppingList.firstOrNull { it.ingredient.id == request.ingredientId && it.recipe != null }
//                print(a)
//                val respond = if (a == null) {
//
//                } else {
//                    shoppingItemDataSource.changeShoppingListItemStatus(ShoppingItem(
//                        id = a.id,
//                        isChecked = a.isChecked,
//                        amount = a.amount + 1,
//                        unit = a.unit
//
//                    ))
//
//
//                }
                val respond = shoppingItemDataSource.insertShoppingList(request)
                call.respond(respond)
            } catch (e: Exception) {
                application.log.error("Fail to insert new shopping item")
            }
        }
        get("user/{id}") {
            val id = call.parameters["id"]
            try {
                val recipe = shoppingItemDataSource.getShoppingListByUserId(id!!)
                call.respond(recipe)
            } catch (e: Exception) {
                application.log.error("Fail to get shopping list")
            }
        }
        put {
            val request = call.receive<ShoppingItem>()
            try {
                val respond = shoppingItemDataSource.changeShoppingListItemStatus(
                    request,
                )
                call.respond(respond)
            } catch (e: Exception) {
                application.log.error("Fail to update shopping list")
            }
        }
        delete("{id}") {
            val id = call.parameters["id"]
            try {
                val recipe = shoppingItemDataSource.deleteShoppingList(id!!)
                call.respond(recipe)
            } catch (e: Exception) {
                application.log.error("Fail to delete shopping list")
            }
        }

    }

}
