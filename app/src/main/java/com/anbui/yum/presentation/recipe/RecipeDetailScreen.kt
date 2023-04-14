package com.anbui.yum.presentation.recipe

//import androidx.compose.material.BackdropValue
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.rememberBackdropScaffoldState
//import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.yum.presentation.recipe.component.BodyItem
import com.anbui.yum.presentation.recipe.component.ImageItem
import com.anbui.yum.presentation.recipe.component.TitleItem
import kotlinx.coroutines.launch


internal val TITLE_HEIGHT = 180.dp
internal val IMAGE_HEIGHT = 400.dp


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun RecipeDetailScreen(
    popUp: () -> Unit,
    recipeId: String,
    openScreen: (String) -> Unit = {},
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(

        sheetPeekHeight = 500.dp,
        sheetContent = {
            TitleItem(
                recipe = uiState.recipe,
                pagerState = pagerState,
                onTabChange = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                },
            )
            BodyItem(
                pagerState = pagerState,
                recipe = uiState.recipe,
                nutrition = uiState.currentNutrition,
                reviews = uiState.review,
            )
        },
    ) {
        ImageItem(recipe = uiState.recipe)
    }
    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }

}


