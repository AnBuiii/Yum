package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.R
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.recipe.component.OverviewListItem
import com.anbui.yum.presentation.recipe.component.RecipeExpandableText
import com.anbui.yum.presentation.recipe.component.RelatedRecipes
import com.anbui.yum.presentation.recipe.component.TabTopBar
import com.anbui.yum.ui.theme.YumGreen
import kotlin.math.roundToInt


@Composable
internal fun OverviewTab(
    recipe: Recipe,
    onAddToCollection: () -> Unit = {},
//    state: BottomSheetScaffoldState,

) {
    val pagePadding = 24.dp
    Box(
        modifier = Modifier.background(Color.White),
    ) {
        LazyColumn {

            item {
                TabTopBar(
                    modifier = Modifier.padding(24.dp),
                    leading = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.clickable { onAddToCollection() },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add_collection),
                                contentDescription = "",
                                tint = YumGreen,
                            )
                            Text(
                                "Add to Collection",
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    },
                    trailing = {},
                )

            }
            item {
                Divider(modifier = Modifier.padding(horizontal = pagePadding))
                Spacer(modifier = Modifier.padding(top = 12.dp))
            }

            val detailList = listOf(
                Triple(
                    Icons.Default.Star,
                    "Ratings",
                    "${recipe.ratings.roundToInt()}",
                ),
                Triple(
                    Icons.Default.Star,
                    "Servings",
                    "${recipe.servings}",
                ),
                Triple(
                    Icons.Default.Star,
                    "Calories per serving",
                    "${recipe.caloriesPerServing}",
                ),
                Triple(
                    Icons.Default.Star,
                    "Total time",
                    "${recipe.totalTimeInMinute}",
                ),
            )

            items(detailList) {
                OverviewListItem(
                    icon = it.first,
                    header = it.second,
                    value = it.third,
                )
                Divider(
                    modifier = Modifier.padding(
                        vertical = 12.dp,
                        horizontal = 24.dp,
                    ),
                )
            }

            item {
                RecipeExpandableText(recipe.note)
                Spacer(modifier = Modifier.height(32.dp))
            }


            val relatedRecipes = listOf(
                Recipe(),
                Recipe(),
                Recipe(),
                Recipe(),
            )

            item {
                RelatedRecipes(
                    relatedRecipes,
                    title = "Related Recipes",
                    trail = "View more",
                )
            }
            item {
                RelatedRecipes(
                    relatedRecipes,
                    title = "Related Recipes",
                    trail = "View more",
                )
            }

        }


    }

//    LaunchedEffect(scroll.)


}
