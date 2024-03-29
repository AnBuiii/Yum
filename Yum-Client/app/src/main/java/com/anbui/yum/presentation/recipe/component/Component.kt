package com.anbui.yum.presentation.recipe.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.anbui.yum.common.component.pagerTabIndicatorOffset
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.YumOrange
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun RecipeScreenTab(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    tabList: List<Pair<String, String>>,
    textSize: TextUnit = 14.sp,
) {
    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier.background(Color.White),
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions,
                    )
                    .padding(horizontal = 8.dp),
                color = YumGreen,
            )

        },
    ) {

        tabList.forEachIndexed { index, detail ->
            Tab(
                selected = index == pagerState.currentPage,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            vertical = 12.dp,
                            horizontal = 6.dp,
                        ),

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
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = YumOrange,
                modifier = Modifier.size(16.dp),
            )
            Text(
                header,
                fontSize = 12.sp,
            )
        }
        Text(
            value,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun RecipeExpandableText(
    text: String,
    collapseMaxLine: Int = 6,
) {
    var isCollapsable by remember { mutableStateOf(false) }
    var isCollapse by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 24.dp,
                end = 24.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box {
            Text(
                text = text,
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White,
                                ),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 24.dp,
            ),
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
        contentPadding = PaddingValues(horizontal = 24.dp),
    ) {

        itemsIndexed(recipes) { index, recipe ->
            Column(
                modifier = Modifier
                    .width(136.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onRecipeTab(recipe.id) },
            ) {
                Image(
                    painter = painterResource(R.drawable.food_1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
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
                            Icons.Default.Star,
                            "",
                            modifier = Modifier.size(16.dp),
                        )
                        Text(
                            "0,00",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    FilledIconButton(
                        onClick = { onAddCollectTab(recipe.id) },
                        modifier = Modifier.size(22.dp),
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

