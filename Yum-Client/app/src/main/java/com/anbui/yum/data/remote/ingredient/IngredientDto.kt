package com.anbui.yum.data.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    val name: String = "Food",
    val protein: Int = 0,
    val carb: Int = 0,
    val fat: Int = 0,
    val cholesterol: Int = 0,
    val sodium: Int = 0,
    val potassium: Int = 0,
    val calcium: Int = 0,
    val iron: Int = 0,
    val id: String,
)
