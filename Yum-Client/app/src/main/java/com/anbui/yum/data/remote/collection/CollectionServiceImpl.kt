package com.anbui.yum.data.remote.collection

import android.util.Log
import com.anbui.yum.common.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CollectionServiceImpl(
    private val client: HttpClient,
) : CollectionService {
    override suspend fun getCollectionByUserId(id: String): List<CollectionDto> {
        return try {
            val respond =
                client.get("${Constants.BASE_URL}/collection/user/$id").body<List<CollectionDto>>()
            respond
        } catch (e: Exception) {
            Log.d(
                "Get collection by user id",
                e.toString(),
            )
            emptyList()
        }
    }
}
