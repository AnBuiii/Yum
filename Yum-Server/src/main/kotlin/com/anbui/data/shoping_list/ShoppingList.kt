package com.anbui.data.shoping_list

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class ShoppingList(
    val userId: String = "",
    val foodId: String = "",
    @BsonId val id: String = ObjectId().toString()
)
