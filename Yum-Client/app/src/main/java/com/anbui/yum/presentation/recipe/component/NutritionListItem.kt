package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anbui.yum.ui.theme.normal

@Composable
fun NutritionListItem(
    modifier: Modifier = Modifier,
    title: String = "PER 1 SERVINGS",
    amount: String = "AMOUNT",
    rda: String = "RDA",
    style: TextStyle = normal,
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp, vertical = 12.dp),
    ) {
        Text(
            title,
            modifier = Modifier.weight(5f),
            style = style,
        )
        Text(
            amount,
            modifier = Modifier.weight(5f),
            style = style,
            textAlign = TextAlign.Center,
        )
        Text(
            rda,
            modifier = Modifier.weight(1f),
            style = style,
        )
    }
}
