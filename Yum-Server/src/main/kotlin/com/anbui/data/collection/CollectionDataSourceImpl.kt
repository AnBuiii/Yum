package com.anbui.data.collection

import org.litote.kmongo.MongoOperator
import org.litote.kmongo.addToSet
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setTo

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

    override suspend fun removeRecipeFromCollection(collectionId: String, recipeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addCollection(collection: Collection): Boolean {
        return collections.insertOne(collection).wasAcknowledged()
    }

    override suspend fun removeCollection(collectionId: String) {
        TODO("Not yet implemented")
    }
}
