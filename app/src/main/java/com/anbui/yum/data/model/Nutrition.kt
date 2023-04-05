package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Nutrition(
    val calories: Int = 0,
    val caloriesFromFat: Int = 0,
    val caloriesFromCarbs: Int = 0,
    val caloriesFromProtein: Int = 0,
    val cholesterol: Int = 0,
    val sodium: Int = 0,
    val potassium: Int = 0,
    val calcium: Int = 0,
    val iron: Int = 0,
)
