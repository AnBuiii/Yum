package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.data.model.IngredientDetail
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen

@Composable
fun NutritionTab() {
    val pagePadding = 24.dp
    val ingredients = listOf(
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "can",
            amount = 4,
            note = "asdasd",
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "helo",
            amount = 4,
            note = "ok bro",
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
            note = "hm",
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
        ),
        IngredientDetail(
            ingredient = "garlic cloves",
            unit = "",
            amount = 4,
        ),
    )
    Column(
        modifier = Modifier.padding(pagePadding),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(Icons.Default.List, contentDescription = "", tint = YumGreen)
            Text("Add All to Shoppubn", fontWeight = FontWeight.SemiBold)
        }
        Divider(modifier = Modifier.padding(vertical = 12.dp))


        ingredients.map { detail ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.Top,
            ) {
                FilledIconButton(
                    onClick = {},
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = YumGreen,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier.size(22.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp),

                    ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(fontSize = 14.sp)) {
                                append("${detail.amount} ${detail.unit} ")
                            }
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)) {
                                append(detail.ingredient)
                            }
                        },
                    )
                    Text(
                        detail.note,
                        color = YumBlack.copy(alpha = 0.7f),
                        fontSize = 12.sp
                    )
                }
            }
        }

    }

}
