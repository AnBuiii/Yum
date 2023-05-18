package com.anbui.data.shoping_list

import com.anbui.data.ingredient.Ingredient
import com.anbui.data.recipe.Recipe
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class ShoppingItemSend(
    val userId: String = "",
    val ingredient: Ingredient,
    val recipe: Recipe?,
    val amount: Double = 0.0,
    val unit: String = "",
    val isChecked: Boolean = false,
    @BsonId val id: String = ObjectId().toString(),
)
