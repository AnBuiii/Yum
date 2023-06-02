package com.anbui.yum.presentation.recipe

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.anbui.yum.common.ext.timeAgo
import com.anbui.yum.presentation.recipe.component.AddToCollection
import com.anbui.yum.presentation.recipe.component.BodyItem
import com.anbui.yum.presentation.recipe.component.ImageItem
import com.anbui.yum.presentation.recipe.component.TitleItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


internal val TITLE_HEIGHT = 200.dp
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
    val hm = rememberStandardBottomSheetState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = hm,
    )
    val coroutineScope = rememberCoroutineScope()
    var visible by remember { mutableStateOf(false) }
    Box {

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 500.dp,
            sheetShape = RectangleShape,
            sheetDragHandle = {},
            sheetContent = {
                Column(
                    modifier = Modifier.heightIn(min = 500.dp),
                ) {
                    TitleItem(
//                modifier = Modifier.pul
                        recipe = uiState.recipe,
                        pagerState = pagerState,
                    )
                    BodyItem(
                        pagerState = pagerState,
                        recipe = uiState.recipe,
                        nutrition = uiState.currentNutrition,
                        reviews = uiState.review,
                        ingredient = uiState.ingredients,
                        getIngredientName = viewModel::getIngredientNameById,
                        getUserInfo = viewModel::getUserInfoById,
                        navigate = openScreen,
                        popUp = popUp,
                        onAddToCollection = {
                            coroutineScope.launch {
                                viewModel.getCollection()
                                delay(300)
                                visible = true
                            }
                        },
                        onAddAllIngredientToShoppingList = {
                            viewModel.addAllIngredientToShoppingList()
                        }

                    )
                }
            },
        ) {
            ImageItem(recipe = uiState.recipe)
        }

        AddToCollection(
            visible = visible,
            onSubmit = { visible = false },
            collections = uiState.collections,
            recipe = uiState.recipe,
            onRemove = viewModel::removeFromCollection,
            onInsert = viewModel::insertToCollection
        )

    }




    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }
    LaunchedEffect(hm.currentValue) {
        if (hm.targetValue == SheetValue.Hidden) {
            hm.show()
        }
        Log.d(
            "time",
            1684555153713L.timeAgo(),
        )

        Log.d(
            "time",
            System.currentTimeMillis().toString(),
        )


    }


}


