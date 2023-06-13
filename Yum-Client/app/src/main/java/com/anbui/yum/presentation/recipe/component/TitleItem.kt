package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.OTHERUSER_SCREEN
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.recipe.TITLE_HEIGHT
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen

@ExperimentalFoundationApi
@Composable
fun TitleItem(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    pagerState: PagerState,
    userName: String = "",
    openUserScreen: (String) ->Unit = {}
) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
//                    .alpha(alpha)
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.widthIn(max = 300.dp),
                ) {
                    Text(
                        text = recipe.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = userName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = YumBlack,
                        modifier = Modifier.clickable { openUserScreen("$OTHERUSER_SCREEN/${recipe.userId}") }
                    )
                }
                FilledIconButton(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = YumGreen,
                        contentColor = Color.White
                    ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

            }
            //tabs
            val tabList =
                listOf(
                    "Overview" to "",
                    "Ingredient" to "${recipe.ingredients?.size ?: 0} items",
                    "Direction" to "${recipe.totalTimeInMinute} minutes",
                    "Nutrition" to "200 calories",
                    "Review" to "2 reviews",
                )

            RecipeScreenTab(
                pagerState = pagerState,
                tabList = tabList,
            )
        }
    }
}
