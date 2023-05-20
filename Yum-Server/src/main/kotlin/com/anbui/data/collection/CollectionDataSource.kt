package com.anbui.data.collection

interface CollectionDataSource {
    suspend fun getCollection(id: String): Collection?
    suspend fun getCollectionsByUserId(userId: String): List<Collection>
    suspend fun addRecipeToCollection(collectionId: String, recipeId: String): Boolean
    suspend fun removeRecipeFromCollection(collectionId: String, recipeId: String)
    suspend fun addCollection(collection: Collection): Boolean
    suspend fun removeCollection(collectionId: String)
}
