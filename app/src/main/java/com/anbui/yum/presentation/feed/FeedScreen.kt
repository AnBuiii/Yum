package com.anbui.yum.presentation.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.yum.R
import com.anbui.yum.common.component.YumRecipeCard
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.common.component.YumTabRow
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.ui.theme.YumOrange


val tabList = listOf("Just for you", "Explore", "Pro")
val cardString =
    listOf("Make a Meal Plan", "Learn from the Pros", "Browse Articles for Inspiration")

@ExperimentalMaterial3Api
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    onRecipeTap: (String) -> Unit = {},
    viewModel: FeedViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    YumSurface(
        modifier = modifier
            .fillMaxSize(),

        ) {
        //
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            FeedTopBar(
                selectedTab = uiState.tabState,
                onTabChange = viewModel::scrollToTab,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(232, 233, 235)),
            ) {
                suggestionCard()
                feed(
                    uiState.recipes,
                    onRecipeTap = {recipeId ->
                        viewModel.onRecipeTap(onRecipeTap, recipeId)
                    },
                )
            }
        }
    }

//    LaunchedEffect(true) {
//        viewModel.getFeedRecipes()
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.feed(recipes: List<Recipe>, onRecipeTap: (String) -> Unit) {
    items(recipes) { recipe ->
        YumRecipeCard(recipe = recipe, onTap = { onRecipeTap(recipe.id) })
    }
}

fun LazyListScope.suggestionCard() {
    item {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            //                    .background(Color(232, 233, 235))
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(cardString) {
                Card(
                    modifier = Modifier
                        .aspectRatio(2.4f)
                        .fillMaxHeight(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.food_1),
                            contentDescription = "",
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop,
                        )
                        Box(
                            modifier = Modifier
                                .weight(4f)
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                it,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun FeedTopBar(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp),
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 22.sp),
                color = YumOrange,
            )
        }

        YumTabRow(
            selectedTab = selectedTab,
            onTabChange = onTabChange,
            tabList = tabList,
            modifier = Modifier.weight(2f),
        )
    }
}
