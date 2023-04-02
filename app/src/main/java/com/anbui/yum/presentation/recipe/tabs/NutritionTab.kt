package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.data.model.nutritionFacts
import com.anbui.yum.presentation.recipe.component.NutritionListItem
import com.anbui.yum.presentation.recipe.component.PieChart
import com.anbui.yum.presentation.recipe.component.TabTopBar
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.semi

@Composable
fun NutritionTab(
    recipe: Recipe,
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
                        Icon(Icons.Default.Settings, contentDescription = "", tint = YumGreen)
                        Text("Edit Dietary Preferences", fontWeight = FontWeight.SemiBold)
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            )

        }

        item {
            NutritionListItem(style = semi)
            Divider(modifier = Modifier.padding(horizontal = 24.dp), color = YumBlack)
        }

        items(nutritionFacts) {
            NutritionListItem(title = it.first, amount = it.second, rda = it.third)
            Divider(modifier = Modifier.padding(horizontal = 24.dp))

        }

    }

}
