package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.domain.model.Nutrition
import com.anbui.yum.presentation.recipe.component.NutritionListItem
import com.anbui.yum.presentation.recipe.component.PieChart
import com.anbui.yum.presentation.recipe.component.TabTopBar
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.semi

@Composable
fun NutritionTab(
    nutrition: Nutrition,
) {
    LazyColumn {

        item {
            TabTopBar(
                modifier = Modifier.padding(24.dp),
                leading = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.clickable { },
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "",
                            tint = YumGreen,
                        )
                        Text(
                            "Edit Dietary Preferences",
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                },
                trailing = {},
            )

        }
        item {
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
        item {
            PieChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                values = listOf(
                    nutrition.caloriesFromProtein,
                    nutrition.caloriesFromCarbs,
                    nutrition.caloriesFromFat,
                ),
            )

        }

        item {
            NutritionListItem(style = semi)
            Divider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = YumBlack,
            )
        }

        item {
            NutritionListItem(
                title = "Calories",
                amount = "${nutrition.calories} cal",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Calories from Fat",
                amount = "${nutrition.caloriesFromFat} cal",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Calories from Carbs",
                amount = "${nutrition.caloriesFromCarbs} cal",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Calories from Protein",
                amount = "${nutrition.caloriesFromProtein} cal",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Cholesterol",
                amount = "${nutrition.cholesterol} mg",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Sodium",
                amount = "${nutrition.sodium} mg",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Potassium",
                amount = "${nutrition.potassium} mg",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Calcium",
                amount = "${nutrition.calcium} mg",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            NutritionListItem(
                title = "Iron",
                amount = "${nutrition.iron} mg",
                rda = "%",
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))

        }

    }

}
