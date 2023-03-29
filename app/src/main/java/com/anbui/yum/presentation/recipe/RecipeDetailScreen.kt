package com.anbui.yum.presentation.recipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.anbui.yum.R
import com.anbui.yum.common.component.YumTabRow
import com.anbui.yum.common.component.YumTabRowPair
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.ui.theme.YumGreen


private val TopBarHeight = 56.dp
private val TITLE_HEIGHT = 200.dp
private val IMAGE_HEIGHT = 400.dp

@Composable
fun RecipeDetailScreen(
    popUp: () -> Unit,
    recipeId: String,
    openScreen: (String) -> Unit = {},
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    val scroll = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        Image(recipe = uiState.recipe)
        Body(scroll)
        Title(scrollValue = scroll.value, uiState.recipe)

    }

    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }
}

@Composable
fun Title(scrollValue: Int, recipe: Recipe) {
    val scrollValueDp = with(LocalDensity.current) { scrollValue.toDp() }
    Box(
        modifier = Modifier
            .padding(top = (IMAGE_HEIGHT - scrollValueDp).coerceAtLeast(0.dp))
            .height(TITLE_HEIGHT)
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column() {
                    Text(text = recipe.title, fontSize = 20.sp)
                    Text(text = recipe.subTitle, fontSize = 16.sp)
                }
                FilledIconButton(onClick = { /*TODO*/ }, shape = CircleShape) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                }

            }
            val tabList =
                listOf(
                    "Overview" to "",
                    "Ingredient" to "",
                    "Direction" to "",
                    "Nutrition" to "",
                    "Review" to "",
                )


            YumTabRowPair(
                selectedTab = 0,
                onTabChange = {},
                tabList = tabList,
            )


        }
    }
}

@Composable
fun HeaderItem(scrollValue: Int, recipe: Recipe) {
    val bodyScrollValueToShowText = with(LocalDensity.current) { IMAGE_HEIGHT.toPx() }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        AnimatedVisibility(
            visible = scrollValue >= bodyScrollValueToShowText.toInt(),
            enter = fadeIn(initialAlpha = 0.2f),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.TopStart),
        ) {
            Text(
                recipe.id, modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        ) {

            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
            }
            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
            }
        }
    }


}

@Composable
private fun Image(
    recipe: Recipe,
) {

    AsyncImage(
        model = recipe.imageUrl,
        contentDescription = "",
        modifier = Modifier
            .height(IMAGE_HEIGHT)
            .fillMaxWidth()
            .background(Color.Black),
        contentScale = ContentScale.FillBounds,
    )
}


@Composable
private fun Body(
//    related: List<SnackCollection>,
    scroll: ScrollState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(scroll)
            .fillMaxWidth(),

        ) {
        Spacer(Modifier.height(IMAGE_HEIGHT + TITLE_HEIGHT + 80.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = 8.dp)
                .clickable { },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("View by", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Your collection", color = YumGreen, fontSize = 12.sp)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                tint = YumGreen,
            )
        }


        listOf("All Yum", "Break fast", "Desserts", "Dinners", "Drinks", "Sides").forEach {
            Box(
                modifier = Modifier
                    .aspectRatio(2.5f)
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_1),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.4f),
                                    Color.Black.copy(alpha = 0.6f),
                                ),
                            ),
                        )
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        it,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text("1", color = Color.White, fontSize = 18.sp)
                        Text("RECIPES", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10000.dp))
}

