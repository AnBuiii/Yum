package com.anbui.yum.screens.recipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RecipeDetailScreen(
    popUp: () -> Unit,
    recipeId: String,
    openScreen: (String) -> Unit = {},
    viewModel: RecipeDetailViewModel = hiltViewModel()
)
{
    Text("recipe")
}