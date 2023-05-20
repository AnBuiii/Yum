package com.anbui.data.collection

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Collection(
    val title: String = "",
    val userId: String = "",
    val recipes: List<String> = emptyList(),
    @BsonId val id: String = ObjectId().toString(),
)
