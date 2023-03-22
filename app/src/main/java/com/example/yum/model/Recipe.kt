package com.example.yum.model


import com.example.yum.RECIPE_DEFAULT_ID
import com.google.firebase.firestore.DocumentId

data class Recipe(
    @DocumentId val id: String = "",
    val title: String = "",
    val userId: String = "",
)

val recipes = listOf(
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Spaghetti",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Bún đậu mắm tôm",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Mì quảng",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Bún bò",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Mì cay",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Bánh canh",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Bánh mì chảo",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Lẩu chay",
        userId = "1"
    ),
    Recipe(
        id = RECIPE_DEFAULT_ID,
        title = "Cơm",
        userId = "1"
    ),
)