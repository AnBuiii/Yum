package com.anbui.data.user

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class User(
    val username: String,
    val password: String,
    @BsonId val id: String = ObjectId().toString()
)