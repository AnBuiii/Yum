package com.anbui.data.user_info

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class UserInfo (
    val userId: String = "",
    val name: String = "",
    val title: String = "",
    val imageUrl: String = "",
    @BsonId val id: String = ObjectId().toString()
)
