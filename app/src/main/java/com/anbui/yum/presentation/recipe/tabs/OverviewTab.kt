package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.presentation.recipe.component.OverviewListItem
import com.anbui.yum.presentation.recipe.component.RecipeExpandableText
import com.anbui.yum.presentation.recipe.component.RelatedRecipes

@Composable
internal fun OverviewTab(
) {
    val pagePadding = 24.dp
    Column(
        modifier = Modifier.padding(pagePadding),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(Icons.Default.List, contentDescription = "")
            Text("Update Collection", fontWeight = FontWeight.SemiBold)
        }

        Divider(modifier = Modifier.padding(vertical = 12.dp))

        listOf(
            Triple(Icons.Default.Star, "Rating", "4.69"),
            Triple(Icons.Default.Star, "Servings", "4.69"),
            Triple(Icons.Default.Star, "Calories per serving", "4.69"),
            Triple(Icons.Default.Star, "Total time", "4.69"),
        ).map {
            OverviewListItem(
                icon = it.first,
                header = it.second,
                value = it.third,
            )
            Divider(modifier = Modifier.padding(vertical = 12.dp))
        }

        RecipeExpandableText()

        Spacer(modifier = Modifier.height(32.dp))

        val relatedRecipes = listOf(Recipe(), Recipe(), Recipe(), Recipe())

        RelatedRecipes(relatedRecipes, title = "Related Recipes", trail = "View more")

        RelatedRecipes(relatedRecipes, title = "Related Recipes", trail = "View more")


    }


}
