package com.anbui.data.recipe

import com.anbui.data.ingredient.IngredientDetail
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Recipe(
    val title: String,
    val subTitle: String,
    val note: String?,
    val ratings: Float,
    val servings: Int,
    val caloriesPerServing: Int,
    val totalTimeInMinute: Int,
    val imageUrl: String,
    val ingredients: List<IngredientDetail>? = null,
    val steps: List<String>?,
    val userId: String,

    @BsonId val id: String = ObjectId().toString(),
)
