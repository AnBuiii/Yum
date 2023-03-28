package com.anbui.yum.presentation.recipe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.anbui.yum.R
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.ui.theme.YumGreen


private val TopBarHeight = 56.dp
private val TitleHeight = 400.dp

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
        Header()
        Body(scroll)
//        HeaderItem(scroll.value, uiState.recipe)

    }

    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
    }
}


@Composable
fun HeaderItem(scrollValue: Int, recipe: Recipe) {
    val bodyScrollValueToShowText = with(LocalDensity.current) { TitleHeight.toPx() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
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
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
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
        model = null,
        contentDescription = "",
        modifier = Modifier
            .height(TitleHeight)
            .fillMaxWidth().background(Color.Black),
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
private fun Header() {
    val headerHeight = 400.dp
    Spacer(
        modifier = Modifier
            .height(TopBarHeight)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
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
        Spacer(Modifier.height(TitleHeight + TopBarHeight))

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

