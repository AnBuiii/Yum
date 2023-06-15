package com.anbui.yum.presentation.recipe.component

//import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anbui.yum.REVIEW_SCREEN
import com.anbui.yum.domain.model.Ingredient
import com.anbui.yum.domain.model.Nutrition
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.domain.model.Review
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.presentation.recipe.tabs.DirectionTab
import com.anbui.yum.presentation.recipe.tabs.IngredientTab
import com.anbui.yum.presentation.recipe.tabs.NutritionTab
import com.anbui.yum.presentation.recipe.tabs.OverviewTab
import com.anbui.yum.presentation.recipe.tabs.ReviewTab


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyItem(
    pagerState: PagerState,
    recipe: Recipe,
    nutrition: Nutrition,
    reviews: List<Review>,
    ingredient: List<Ingredient>,
    getIngredientName: suspend (String) -> String,
    getUserInfo: suspend (String) -> UserInfo,
    navigate: (String) -> Unit,
    popUp: () -> Unit,
    onAddToCollection: () -> Unit = {},
    onAddAllIngredientToShoppingList: () -> Unit = {},
    onAddToMealPlan: () -> Unit = {},
//    state: BottomSheetScaffoldState

) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
//        Spacer(Modifier.height(TITLE_HEIGHT))
        HorizontalPager(
            pageCount = 5,
            state = pagerState,
            modifier = Modifier.background(Color.White),
            verticalAlignment = Alignment.Top,

            ) { index ->
            when (index) {
                0 -> OverviewTab(
                    recipe = recipe,
                    onAddToCollection = onAddToCollection,
                )

                1 -> IngredientTab(
                    recipe = recipe,
                    ingredient = ingredient,
                    getIngredientName = getIngredientName,
                    onAddAllRecipeToShoppingList = onAddAllIngredientToShoppingList,
                )

                2 -> DirectionTab(
                    recipe = recipe,
                    onAddToMealPlan = onAddToMealPlan,
                )

                3 -> NutritionTab(nutrition)
                4 -> ReviewTab(
                    reviews,
                    getUserInfo,
                    openReviewScreen = {
                        navigate("$REVIEW_SCREEN/${recipe.id}")
                    },
                    popUp = popUp,
                )
            }


        }

    }
}
