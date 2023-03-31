package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NutritionTab() {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.Red),
    ) {
        Text("Nutrition")
    }
}
