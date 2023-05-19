package com.anbui.yum.presentation.recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.anbui.yum.presentation.recipe.component.BodyItem
import com.anbui.yum.presentation.recipe.component.ImageItem
import com.anbui.yum.presentation.recipe.component.TitleItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


internal val TITLE_HEIGHT = 180.dp
internal val IMAGE_HEIGHT = 400.dp


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun RecipeDetailScreen(
    popUp: () -> Unit,
    recipeId: String,
    openScreen: (String) -> Unit = {},
    viewModel: RecipeDetailViewModel = getViewModel(),
) {

    val uiState by viewModel.uiState
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()



    BottomSheetScaffold(

        sheetPeekHeight = 500.dp,
        sheetShape = RectangleShape,
        sheetDragHandle = {},
        sheetContent = {
            TitleItem(
//                modifier = Modifier.pul
                recipe = uiState.recipe,
                pagerState = pagerState
            )
            BodyItem(
                pagerState = pagerState,
                recipe = uiState.recipe,
                nutrition = uiState.currentNutrition,
                reviews = uiState.review,
                ingredient = uiState.ingredients,
                getIngredientName = viewModel::getIngredientNameById,
                getUserInfo = viewModel::getUserInfoById,
                navigate = openScreen

            )
        },
    ) {
        ImageItem(recipe = uiState.recipe)
    }
    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }

}


