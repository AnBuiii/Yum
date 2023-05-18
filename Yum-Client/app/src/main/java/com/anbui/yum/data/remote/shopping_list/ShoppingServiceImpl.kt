package com.anbui.yum.data.remote.shopping_list

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ShoppingServiceImpl(private val client: HttpClient) : ShoppingService {
    override suspend fun getShoppingList(userId: String): List<ShoppingItemDto> {
        return try {
            client.get("${BASE_URL}/shopping/user/$userId").body()
        } catch (e: Exception) {
            Log.d(
                "Review service get recipe error",
                e.toString(),
            )
            listOf()
        }
    }

    override suspend fun changeShoppingItemStatus(shoppingItemSendDto: ShoppingItemSendDto): Boolean {
        return try {
            client.put("$BASE_URL/shopping") {
                contentType(ContentType.Application.Json)
                setBody(
                    shoppingItemSendDto
                )
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Change item status fail",
                e.toString(),
            )
            false

        }
    }

    override suspend fun addShoppingItem(shoppingItemSendDto: ShoppingItemSendDto): Boolean {
        return try {
            client.post("$BASE_URL/shopping") {
                contentType(ContentType.Application.Json)
                setBody(
                    shoppingItemSendDto
                )
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Change item status fail",
                e.toString(),
            )
            false

        }
    }

    override suspend fun removeShoppingItem(id: String): Boolean {
        return try {
            client.delete("$BASE_URL/shopping/$id").body()
        } catch (e: Exception) {
            Log.d(
                "delete shoping item fail",
                e.toString(),
            )
            false

        }
    }
}
