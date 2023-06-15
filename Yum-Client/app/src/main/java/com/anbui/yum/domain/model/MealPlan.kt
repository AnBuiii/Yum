package com.anbui.yum.domain.model

import java.time.LocalDateTime

data class MealPlan(
    val recipeId: String = "",
    val title: String = "",
    val imageUrl: String = "https://images.unsplash.com/photo-1680199994489-22b5f4ccc620?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String = "",
)
