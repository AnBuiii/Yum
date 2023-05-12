package com.anbui.yum.data.remote.shopping_list

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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

    override suspend fun changeShoppingItemStatus(id: String, isChecked: Boolean) {
        try {
            client.put("$BASE_URL/shopping") {
                contentType(ContentType.Application.Json)
                setBody(ShoppingItemDto(id = id, isChecked = isChecked))
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Submit review fail",
                e.toString(),
            )
        }
    }
}
