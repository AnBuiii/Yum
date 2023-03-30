package com.anbui.yum.presentation.recipe

import android.util.Log
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.viewpager.widget.ViewPager
import coil.compose.AsyncImage
import com.anbui.yum.data.model.Recipe


private val TopBarHeight = 56.dp
private val TITLE_HEIGHT = 180.dp
private val IMAGE_HEIGHT = 400.dp


@Composable
fun RecipeDetailScreen(
    popUp: () -> Unit,
    recipeId: String,
    openScreen: (String) -> Unit = {},
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {
    val HM = with(LocalDensity.current) { IMAGE_HEIGHT.toPx() }
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.uiState
    val scroll = rememberScrollState()


    Box(modifier = Modifier.fillMaxSize()) {

        Image(recipe = uiState.recipe)
        Body(scroll)
        Title(
            scrollValue = scroll.value,
            uiState.recipe,
            uiState.selectedTab,
            viewModel::onChangeTab,
        )
        HeaderItem()

    }

    LaunchedEffect(true) {
        viewModel.getRecipe(recipeId)
        Log.d("Scroll", "Still")
    }

    LaunchedEffect(scroll.isScrollInProgress) {
        if (scroll.value > 0 && scroll.value < HM)
            scroll.animateScrollTo(HM.toInt(),
                animationSpec = spring()
            )


    }
    ViewPager()
}

@Composable
fun Title(scrollValue: Int, recipe: Recipe, selectedTab: Int, onTabChange: (Int) -> Unit) {
    val scrollValueDp = with(LocalDensity.current) { scrollValue.toDp() }
    val alpha = (IMAGE_HEIGHT - scrollValueDp).coerceAtLeast(0.dp) * 2 / IMAGE_HEIGHT
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
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {

            // recipe info
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).alpha(alpha),
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
                selectedTab = selectedTab,
                onTabChange = onTabChange,
                tabList = tabList,
            )


        }
    }
}


@Composable
fun HeaderItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 24.dp, end = 24.dp),
        ) {

            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "")
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
        Spacer(Modifier.height(IMAGE_HEIGHT + TITLE_HEIGHT))

        Spacer(modifier = Modifier.height(1000.dp))
    }
}

