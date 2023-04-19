package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.recipe.component.TabTopBar
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen

@Composable
internal fun IngredientTab(
    recipe: Recipe,
) {
    val pagePadding = 24.dp
    LazyColumn() {
        item {
            TabTopBar(
                modifier = Modifier.padding(24.dp),
                leading = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.clickable { },
                    ) {
                        Icon(Icons.Default.List, contentDescription = "", tint = YumGreen)
                        Text("Add All to Shopping List", fontWeight = FontWeight.SemiBold)
                    }
                },
                trailing = {},
            )

        }
        item {
            Divider(modifier = Modifier.padding(horizontal = pagePadding))
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }


        items(recipe.ingredients ?: listOf()) { detail ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.Top,
            ) {
                FilledIconButton(
                    onClick = {},
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = YumGreen,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier.size(22.dp),
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
                                append(detail.ingredientId)
                            }
                        },
                    )
                    Text(
                        detail.note,
                        color = YumBlack.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                    )
                }
            }
        }

    }
}
