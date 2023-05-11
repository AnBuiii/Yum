package com.anbui.data.ingredient

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Ingredient(
    val name: String = "Food",
    val protein: Int = 0,
    val carb: Int = 0,
    val fat: Int = 0,
    val cholesterol: Int = 0,
    val sodium: Int = 0,
    val potassium: Int = 0,
    val calcium: Int = 0,
    val iron: Int = 0,
    val tag: String = "",
    @BsonId val id: String = ObjectId().toString(),
)
