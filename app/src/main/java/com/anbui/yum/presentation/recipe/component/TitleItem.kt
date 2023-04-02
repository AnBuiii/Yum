package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.presentation.recipe.TITLE_HEIGHT

@ExperimentalFoundationApi
@Composable
fun TitleItem( recipe: Recipe, pagerState: PagerState, onTabChange: (Int) -> Unit) {
//    val scrollValueDp = with(LocalDensity.current) { scrollValue.toDp() }
//    val alpha = (IMAGE_HEIGHT - scrollValueDp).coerceAtLeast(0.dp) * 2 / IMAGE_HEIGHT
    Box(
        modifier = Modifier
//            .padding(top = (IMAGE_HEIGHT - scrollValueDp).coerceAtLeast(0.dp))
            .height(TITLE_HEIGHT)
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {

            // recipe info
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
//                    .alpha(alpha)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.widthIn(max = 300.dp),
                ) {
                    Text(text = recipe.title, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = recipe.subTitle, fontSize = 16.sp)
                }
                FilledIconButton(onClick = { /*TODO*/ }, shape = CircleShape) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                }

            }
            //tabs
            val tabList =
                listOf(
                    "Overview" to "",
                    "Ingredient" to "20 items",
                    "Direction" to "20 minutes",
                    "Nutrition" to "200 calories",
                    "Review" to "2 reviews",
                )

            RecipeScreenTab(
                selectedTab = pagerState.currentPage,
                onTabChange = onTabChange,
                tabList = tabList,
            )


        }
    }
}
