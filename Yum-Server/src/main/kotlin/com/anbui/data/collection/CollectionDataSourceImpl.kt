package com.anbui.data.collection

import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class CollectionDataSourceImpl(db: CoroutineDatabase) : CollectionDataSource {
    private val collections = db.getCollection<Collection>()

    override suspend fun getCollection(id: String): Collection? {
        return collections.findOne(Collection::id eq id)
    }

    override suspend fun getCollectionsByUserId(userId: String): List<Collection> {
        return collections.find(Collection::userId eq userId).toList()
    }

    override suspend fun addRecipeToCollection(collectionId: String, recipeId: String): Boolean {
        return collections.updateOne(Collection::id eq collectionId, addToSet(Collection::recipes, recipeId))
            .wasAcknowledged()
    }

    override suspend fun removeRecipeFromCollection(collectionId: String, recipeId: String): Boolean {
        return collections.updateOne(Collection::id eq collectionId, pull(Collection::recipes, recipeId))
            .wasAcknowledged()
    }

    override suspend fun addCollection(collection: Collection): Boolean {
        return if (collections.findOne(Collection::title eq collection.title) != null) {
            false
        } else {
            collections.insertOne(
                collection,
            ).wasAcknowledged()
        }
    }

    override suspend fun removeCollection(collectionId: String) {
        TODO("Not yet implemented")
    }
}
