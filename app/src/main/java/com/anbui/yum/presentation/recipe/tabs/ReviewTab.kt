package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.yum.data.model.Recipe

@Composable
fun ReviewTab(
    recipe: Recipe
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Review")
    }
}
