package com.anbui.yum.domain.model

import kotlinx.serialization.Serializable

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
    val id: String,
)


val nutritionFacts = listOf(
    Triple("Calories", "460 cal", "35%"),
    Triple("Calories from Fat", "130 cal", "12%"),
    Triple("Calories from Carbs", "123 cal", "13%"),
    Triple("Calories from Protein", "", "31%"),
    Triple("Cholesterol", "123 mg", "23%"),
    Triple("Sodium", "123", "%"),
    Triple("Potassium", "123", "%"),
    Triple("Calcium", "123", "%"),
    Triple("Iron", "123", "23%"),
)

