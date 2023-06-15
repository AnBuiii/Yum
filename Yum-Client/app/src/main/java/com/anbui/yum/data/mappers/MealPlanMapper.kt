package com.anbui.yum.data.mappers

import com.anbui.yum.data.local.meal_plan.MealPlanEntity
import com.anbui.yum.domain.model.MealPlan

fun MealPlanEntity.toMealPlan(): MealPlan {
    return MealPlan(
        recipeId = recipeId,
        title = title,
        imageUrl = imageUrl,
        time = time,
        isDone = isDone,
        message = message,
    )
}

fun MealPlan.toMealPlanEntity(): MealPlanEntity {
    return MealPlanEntity(
        recipeId = recipeId,
        title = title,
        imageUrl = imageUrl,
        time = time,
        isDone = isDone,
        message = message,
    )
}
