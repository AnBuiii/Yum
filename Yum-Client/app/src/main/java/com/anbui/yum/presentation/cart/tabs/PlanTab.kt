package com.anbui.yum.presentation.cart.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.domain.model.MealPlan
import com.anbui.yum.presentation.cart.components.MealPlanItem
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlanTab(
    mealPlans: List<MealPlan> = emptyList(),
    onChangeTime: (String) -> Unit = {},
    onDoneTap: (String, Boolean) -> Unit = { _, _ -> },
    onRecipeTap: (String) -> Unit
) {
    val now = LocalDateTime.now()
    var groupedItems by remember { mutableStateOf(mealPlans.groupBy { now.isAfter(it.time) }) }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn {
            groupedItems.forEach { (isAfter, subMealPlans) ->
                stickyHeader {
                    Text(
                        text = buildAnnotatedString {
                            if (!isAfter) append("Overdue") else append("Incoming")
                            withStyle(style = SpanStyle(color = YumGreen)) {
                                append("(${subMealPlans.size})")
                            }
                        },
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = YumBlack,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp,
                        ),
                    )
                }
                items(subMealPlans) { mealPlan ->
                    MealPlanItem(
                        mealPlan = mealPlan,
                        onTimeTap = { onChangeTime(mealPlan.recipeId) },
                        onDoneTap = {
                            onDoneTap(mealPlan.recipeId, !mealPlan.isDone)
                        },
                        onTap = {
                            onRecipeTap(mealPlan.recipeId)
                        }
                    )
                    YumDivider(
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp,
                        ),
                    )
                }
            }


        }

    }
    LaunchedEffect(mealPlans) {
        groupedItems = mealPlans.groupBy { it.time.isAfter(now) }
    }
}
