package com.anbui.yum.data.remote.collection

import android.util.Log
import com.anbui.yum.common.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

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

    override suspend fun getCollectionById(id: String): CollectionDto {
        return try {
            val respond =
                client.get("${Constants.BASE_URL}/collection/$id").body<CollectionDto>()
            respond
        } catch (e: Exception) {
            Log.d(
                "Get collection by id fail",
                e.toString(),
            )
            CollectionDto()
        }
    }

    override suspend fun removeRecipeFromCollection(
        recipeId: String,
        collectionId: String,
    ): Boolean {
        return try {
            client.put("${Constants.BASE_URL}/collection/$collectionId") {
                setBody(recipeId)
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Remove recipe fail",
                e.toString(),
            )
            false
        }
    }

    override suspend fun insertCollection(collection: CollectionDto): Boolean {
        return try {
            client.post("${Constants.BASE_URL}/collection") {
                contentType(ContentType.Application.Json)
                setBody(collection)
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Insert collection fail",
                e.toString(),
            )
            false
        }
    }

    override suspend fun insertRecipeInCollection(recipeId: String, collectionId: String): Boolean {
        return try {
            client.post("${Constants.BASE_URL}/collection/$collectionId") {
                setBody(recipeId)
            }.body()
        } catch (e: Exception) {
            Log.d(
                "Remove recipe fail",
                e.toString(),
            )
            false
        }
    }
}
