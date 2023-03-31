package com.anbui.yum.presentation.recipe

import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.yum.presentation.recipe.component.BodyItem
import com.anbui.yum.presentation.recipe.component.HeaderItem
import com.anbui.yum.presentation.recipe.component.ImageItem
import com.anbui.yum.presentation.recipe.component.TitleItem
import kotlinx.coroutines.launch


private val TopBarHeight = 56.dp
internal val TITLE_HEIGHT = 180.dp
internal val IMAGE_HEIGHT = 400.dp


@ExperimentalFoundationApi
@Composable
fun RecipeDetailScreen(
    popUp: () -> Unit,
    recipeId: String,
    openScreen: (String) -> Unit = {},
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()
    val imageHeightPx = with(LocalDensity.current) { IMAGE_HEIGHT.toPx() }

    Box(modifier = Modifier.fillMaxSize()) {

        ImageItem(recipe = uiState.recipe)
        BodyItem(
            scrollState = scrollState,
            pagerState = pagerState,
        )
        TitleItem(
            scrollValue = scrollState.value,
            recipe = uiState.recipe,
            pagerState = pagerState,
            onTabChange = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it)
                }
            },

            )
        HeaderItem()

    }

    // get recipe
    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }


    LaunchedEffect(scrollState.isScrollInProgress) {
        if (scrollState.value > 0 && scrollState.value < imageHeightPx) {
            scrollState.animateScrollTo(
                imageHeightPx.toInt(),
                animationSpec = spring(),
            )
        }


    }

}


