package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.recipe.component.TabTopBar
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen

@Composable
fun DirectionTab(
    recipe: Recipe,
    onAddToMealPlan: () -> Unit = {},
) {
    LazyColumn {
        item {
            TabTopBar(
                modifier = Modifier.padding(24.dp),
                leading = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.clickable { onAddToMealPlan() },
                    ) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "",
                            tint = YumGreen,
                        )
                        Text(
                            "Add to Meal Plan",
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                },
                trailing = {},
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(YumBlack.copy(0.1f))
                    .padding(16.dp),
            ) {
                Text("Total time")
                Text("${recipe.totalTimeInMinute} minutes")
            }
        }
        itemsIndexed(recipe.steps ?: listOf()) { index, direction ->
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    "${index + 1}",
                    modifier = Modifier.width(60.dp),
                    color = YumGreen,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    direction,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 24.dp),
                    lineHeight = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
        }
    }
}
