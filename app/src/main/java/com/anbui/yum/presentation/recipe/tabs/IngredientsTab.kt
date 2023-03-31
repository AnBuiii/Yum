package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun IngredientTab() {
    Column(
        modifier = Modifier.fillMaxWidth().height(200.dp),
    ) {
        Text("Ingredient")
    }
}
