package com.anbui.data.review

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Review(
    val message: String = "",
    val timestamp: Long = 0L,
    val rating: Int = 0,
    val userId: String = "",
    val recipeId: String = "",
    @BsonId val id: String = ObjectId().toString(),
)

