package com.anbui.yum.presentation.recipe

//import androidx.compose.material.BackdropValue
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetScaffold
//import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
//    val scaffoldState = rememberBottomSheetScaffoldState()


//    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
//    BackdropScaffold(
//        scaffoldState = scaffoldState,
//        appBar = {},
//        peekHeight = 0.dp,
//        headerHeight = 0.dp,
//        backLayerBackgroundColor = Color.White,
//        frontLayerShape = RectangleShape,
//        frontLayerElevation = 0.dp,
//        backLayerContent = {
//            Box {
//                ImageItem(recipe = uiState.recipe)
//                HeaderItem()
//            }
//
//        },
//        frontLayerScrimColor = Color.Transparent,
//        frontLayerContent = {
//            TitleItem(
//                recipe = uiState.recipe,
//                pagerState = pagerState,
//                onTabChange = {
//                    coroutineScope.launch {
//                        pagerState.animateScrollToPage(it)
//                    }
//                },
//            )
//            BodyItem(
//                pagerState = pagerState,
//                recipe = uiState.recipe,
//                nutrition = uiState.currentNutrition,
//                reviews = uiState.review
//            )
//        },
//    )


    BottomSheetScaffold(
//        scaffoldState = scaffoldState,
//        sheetSwipeEnabled = false,

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
//                state = scaffoldState,
            )
        },
    ) {
        Box {
            ImageItem(recipe = uiState.recipe)
            Box(
                modifier = Modifier.clip(RectangleShape).border(20.dp, color = Color.Blue)
                    .size(300.dp)
                    .background(Color.Red)
                    .clip(
                        CircleShape,
                    )
                ,
            ) {

            }
//            HeaderItem()
        }
    }
//    LaunchedEffect(scaffoldState.bottomSheetState.currentValue) {
//        Log.d("SCROLL", "asd")
//    }

    // get recipe
    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }

}


