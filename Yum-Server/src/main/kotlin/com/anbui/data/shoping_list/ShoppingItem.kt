package com.anbui.data.shoping_list

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class ShoppingItem(
    val userId: String = "",
    val ingredientId: String = "",
    val recipeId: String? = "",
    val amount: Double = 0.0,
    val unit: String = "",
    val isChecked: Boolean = false,
    @BsonId val id: String = ObjectId().toString()
)
