package com.anbui.yum.data.remote.recipe

import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.domain.model.IngredientDetail
import com.anbui.yum.domain.model.Recipe
import kotlinx.serialization.Serializable


@Serializable
data class RecipeDto(
    val title: String = "",
    val subTitle: String = "",
    val note: String = "",
    val ratings: Float = 0f,
    val servings: Int = 0,
    val caloriesPerServing: Int = 0,
    val totalTimeInMinute: Int = 0,
    val imageUrl: String = "https://images.unsplash.com/photo-1680199994489-22b5f4ccc620?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
    val ingredients: List<IngredientDetail>? = null,
    val steps: List<String>? = null,
    val userId: String = "",
    val id: String = "",
)




