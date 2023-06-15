package com.anbui.yum.data.local.meal_plan

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class MealPlanEntity(
    val title: String = "",
    val imageUrl: String = "https://images.unsplash.com/photo-1680199994489-22b5f4ccc620?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String = "",
    val isDone: Boolean = false,
    @PrimaryKey val recipeId: String = "",
)
