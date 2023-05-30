package com.anbui.yum.data.remote.collection

interface CollectionService {
    suspend fun getCollectionByUserId(id: String) : List<CollectionDto>
}
