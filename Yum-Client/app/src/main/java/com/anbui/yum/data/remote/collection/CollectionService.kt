package com.anbui.yum.data.remote.collection

interface CollectionService {
    suspend fun getCollectionByUserId(id: String) : List<CollectionDto>
    suspend fun getCollectionById(id: String): CollectionDto
    suspend fun insertRecipeInCollection(recipeId: String, collectionId: String): Boolean
    suspend fun removeRecipeFromCollection(recipeId: String, collectionId: String): Boolean
    suspend fun insertCollection(collection: CollectionDto): Boolean

}
