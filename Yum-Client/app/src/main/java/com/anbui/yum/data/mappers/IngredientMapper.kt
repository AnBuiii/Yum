package com.anbui.yum.data.mappers

import com.anbui.yum.data.remote.ingredient.IngredientDto
import com.anbui.yum.domain.model.Ingredient

fun IngredientDto.toIngredient() : Ingredient{
    return Ingredient(
        name,
        protein,
        carb,
        fat,
        cholesterol,
        sodium,
        potassium,
        calcium,
        iron,
        id,
    )
}
