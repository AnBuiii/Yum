package com.anbui.yum.data.mappers

import com.anbui.yum.data.remote.ingredient.IngredientDto
import com.anbui.yum.domain.model.Ingredient

fun IngredientDto.toIngredient() : Ingredient{
    return Ingredient(
        name = name,
        protein = protein,
        carb = carb,
        fat = fat,
        cholesterol = cholesterol,
        sodium = sodium,
        potassium = potassium,
        calcium = calcium,
        iron = iron,
        tag = tag,
        id = id,
    )
}
