package com.anbui.data.collection

import com.anbui.data.recipe.Recipe
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class CollectionSend(
    val title: String = "",
    val userId: String = "",
    val recipes: List<Recipe> = emptyList(),
    @BsonId val id: String = ObjectId().toString(),
)
