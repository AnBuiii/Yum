package com.anbui.yum.presentation.recipe.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.data.model.Recipe
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.YumOrange

@ExperimentalFoundationApi
@Composable
fun RecipeScreenTab(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit,
    tabList: List<Pair<String, String>>,
    textSize: TextUnit = 14.sp,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier.background(Color.White),
        edgePadding = 0.dp,
    ) {

        tabList.forEachIndexed { index, detail ->
            Tab(
                selected = index == selectedTab,
                onClick = { onTabChange(index) },
                modifier = Modifier.background(Color.White).padding(horizontal = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 12.dp, horizontal = 6.dp),

//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = detail.first,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                    Text(
                        text = detail.second,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                    )
                }
            }
        }
    }
}


@Composable
fun OverviewListItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    header: String,
    value: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = YumOrange,
                modifier = Modifier.size(16.dp),
            )
            Text("Rating", fontSize = 12.sp)
        }
        Text("4,49", fontSize = 12.sp)
    }
}

@Composable
fun RecipeExpandableText(
    collapseMaxLine: Int = 6,

    ) {
    var isCollapsable by remember { mutableStateOf(false) }
    var isCollapse by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box {
            Text(
                text = "sajdhakdjhaIt is a long established fact that a reader will be distracted by tahdjakkkkhdaksjdhajdkahjdkasdhjalhfjsfjasfkalfhjasfhasjfahsflashfjaklsdfjashfakslfjashdfjdksalskdjfhasjdkflasdjfhasjdkflasdjfhasdjfkalskdfjhsjdkalskdfjhjkdlkjhasdfsdfjlkjsdfasdfhjklkjhsfsdfklkjfasdfhjklkjhsfasdffhe readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).kjdhka",
                maxLines = if (isCollapse) collapseMaxLine else Int.MAX_VALUE,
                color = YumBlack,
                fontSize = 12.sp,
                onTextLayout = {
                    if (it.lineCount >= collapseMaxLine) isCollapsable = true
                },
                textAlign = TextAlign.Justify,
                modifier = Modifier.animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                ),
                lineHeight = 16.sp,
            )
            if (isCollapsable && isCollapse) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(110.dp).background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White),
                        ),
                    ),
                )
            }

        }

        if (isCollapsable) {
            Text(
                if (isCollapse) "Read more" else "Read less",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = YumGreen,
                modifier = Modifier.clickable {
                    isCollapse = !isCollapse
                },
            )

        }
    }

    LaunchedEffect(isCollapsable) {
        if (isCollapsable) isCollapse = true
    }
}

@Composable
fun RelatedRecipes(
    recipes: List<Recipe>,
    title: String,
    trail: String,
    onTrailTap: () -> Unit = {},
    onRecipeTab: (String) -> Unit = {},
    onAddCollectTab: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        Text(
            trail,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = YumGreen,
            modifier = Modifier.clickable { onTrailTap() },
        )

    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        itemsIndexed(recipes) { index, recipe ->
            Column(
                modifier = Modifier.width(136.dp).clip(RoundedCornerShape(10.dp))
                    .clickable { onRecipeTab(recipe.id) },
            ) {
                Image(
                    painter = painterResource(R.drawable.food_1),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Gray.copy(alpha = 0.2f))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Default.Star, "",
                            modifier = Modifier.size(16.dp),
                        )
                        Text("0,00", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                    FilledIconButton(
                        onClick = { onAddCollectTab(recipe.id) }, modifier = Modifier.size(22.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = YumGreen,
                            contentColor = Color.White,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier,
                        )
                    }
                }
                Text(
                    "Lemon Chicken with Feta Orzo",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 14.sp,
                )
            }
        }
    }
}

