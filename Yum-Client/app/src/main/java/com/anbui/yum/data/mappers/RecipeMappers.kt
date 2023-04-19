package com.anbui.yum.data.mappers

import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.data.remote.recipe.RecipeDto
import com.anbui.yum.domain.model.Recipe


fun RecipeDto.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        title,
        subTitle,
        note,
        ratings,
        servings,
        caloriesPerServing,
        totalTimeInMinute,
        imageUrl,
        id = id
    )
}

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        title = title,
        subTitle = subTitle,
        note = note,
        ratings = ratings,
        servings = servings,
        caloriesPerServing = caloriesPerServing,
        totalTimeInMinute = totalTimeInMinute,
        imageUrl = imageUrl,
        ingredients = ingredients,
        steps = steps,
        userId = userId,
        id = id,
    )
}


fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        title = title,
        subTitle = subTitle,
        note = note,
        ratings = ratings,
        servings = servings,
        caloriesPerServing = caloriesPerServing,
        totalTimeInMinute = totalTimeInMinute,
        imageUrl = imageUrl,
        userId = userId,
        id = id,
    )
}
