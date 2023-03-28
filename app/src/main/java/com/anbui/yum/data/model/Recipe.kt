package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class  Recipe(
    val title: String = "",
    val subTitle: String = "",
    val ratings: Float = 0f,
    val servings: Int = 0,
    val caloriesPerServing: Int = 0,
    val totalTimeInMinute: Int = 0,
    val imageUrl: String = "",
    val steps: List<Step>? = null,
    val userId: String = "",
    val id: String = "",
)
