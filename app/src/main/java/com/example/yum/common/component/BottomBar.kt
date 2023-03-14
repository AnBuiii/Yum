package com.example.yum.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import com.example.yum.HomeScreenSection
import java.util.*

@Composable
fun RowScope.YumNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = YumNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = YumNavigationDefaults.navigationContentColor(),
            selectedTextColor = YumNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = YumNavigationDefaults.navigationContentColor(),
            indicatorColor = YumNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun YumBottomBar(
    tabs: Array<HomeScreenSection>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.navigationBarsPadding(),
        contentColor = YumNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
    ) {
        val currentSection = tabs.firstOrNull { it.route == currentRoute }
        val configuration = LocalConfiguration.current
        val currentLocale: Locale =
            ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault()

        tabs.forEach { section ->
            YumNavigationBarItem(
                selected = section == currentSection,
                onClick = { navigateToRoute(section.route) },
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = null,
                    )
                },
//                label = { Text(stringResource(section.title).uppercase(currentLocale)) },
            )

        }

    }
}

object YumNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}


//@Composable
//fun YumBottomBar(
//    tabs: Array<HomeScreenSection>,
//    currentRoute: String,
//    navigateToRoute: (String) -> Unit,
//    color: Color = MaterialTheme.colorScheme.surfaceVariant,
//    contentColor: Color = MaterialTheme.colorScheme.outline,
//) {
//    val routes = remember { tabs.map { it.route } }
//    val currentSection = tabs.first { it.route == currentRoute }
//
//    YumSurface(
//        color = color,
//        contentColor = contentColor,
//        shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50)
//    ) {
//        val springSpec = SpringSpec<Float>(
//            stiffness = 400f,
//            dampingRatio = 0.8f
//        )
//        YumBottomNavLayout(
//            selectedIndex = currentSection.ordinal,
//            itemCount = routes.size,
//            indicator = { YumBottomNavIndicator() },
//            animSpec = springSpec,
//            modifier = Modifier.navigationBarsPadding()
//        ) {
//            val configuration = LocalConfiguration.current
//            val currentLocale: Locale =
//                ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault()
//
//            tabs.forEach { section ->
//                val selected = section == currentSection
//                val tint by animateColorAsState(
//                    if (selected) {
//                        MaterialTheme.colorScheme.onSurfaceVariant
//                    } else {
//                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f)
//                    }
//                )
//
//                val text = stringResource(section.title).uppercase(currentLocale)
//
//                YumBottomNavigationItem(
//                    icon = {
//                        Icon(
//                            imageVector = section.icon,
//                            tint = tint,
//                            contentDescription = text
//                        )
//                    },
//                    text = {
//                        Text(
//                            text = text,
//                            color = tint,
////                            style = MaterialTheme.typography.displayMedium,
//                            maxLines = 1
//                        )
//                    },
//                    selected = selected,
//                    onSelected = { navigateToRoute(section.route) },
//                    animSpec = springSpec,
//                    modifier = BottomNavigationItemPadding
//                        .clip(BottomNavIndicatorShape)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun YumBottomNavLayout(
//    selectedIndex: Int,
//    itemCount: Int,
//    animSpec: AnimationSpec<Float>,
//    indicator: @Composable BoxScope.() -> Unit,
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit,
//) {
//    val selectionFractions = remember(itemCount) {
//        List(itemCount) { i ->
//            Animatable(if (i == selectedIndex) 1f else 0f)
//        }
//    }
//    selectionFractions.forEachIndexed { index, selectionFraction ->
//        val target = if (index == selectedIndex) 1f else 0f
//        LaunchedEffect(target, animSpec) {
//            selectionFraction.animateTo(target, animSpec)
//        }
//    }
//
//    val indicatorIndex = remember { Animatable(0f) }
//    val targetIndicatorIndex = selectedIndex.toFloat()
//    LaunchedEffect(targetIndicatorIndex) {
//        indicatorIndex.animateTo(targetIndicatorIndex, animSpec)
//    }
//
//    Layout(
//        modifier = modifier
//            .height(BottomNavHeight)
//            .clip(RoundedCornerShape(20.dp)),
//        content = {
//            content()
//            Box(Modifier.layoutId("indicator"), content = indicator)
//        }
//    ) { measurables, constraints ->
//        check(itemCount == (measurables.size - 1))
//
//        val unselectedWidth = constraints.maxWidth / (itemCount + 1)
//        val selectedWidth = 2 * unselectedWidth
//        val indicatorMeasurable = measurables.first { it.layoutId == "indicator" }
//
//        val itemPlaceables = measurables
//            .filterNot { it == indicatorMeasurable }
//            .mapIndexed { index, measurable ->
//                // Animate item's width based upon the selection amount
//                val width =
//                    lerp(unselectedWidth, selectedWidth, selectionFractions[index].value)
//                measurable.measure(
//                    constraints.copy(
//                        minWidth = width,
//                        maxWidth = width
//                    )
//                )
//            }
//        val indicatorPlaceable = indicatorMeasurable.measure(
//            constraints.copy(
//                minWidth = selectedWidth,
//                maxWidth = selectedWidth
//            )
//        )
//
//        layout(
//            width = constraints.maxWidth,
//            height = itemPlaceables.maxByOrNull { it.height }?.height ?: 0
//        ) {
//            val indicatorLeft = indicatorIndex.value * unselectedWidth
//            indicatorPlaceable.placeRelative(x = indicatorLeft.toInt(), y = 0)
//            var x = 0
//            itemPlaceables.forEach { placeable ->
//                placeable.placeRelative(x = x, y = 0)
//                x += placeable.width
//            }
//        }
//    }
//}
//
//@Composable
//fun YumBottomNavigationItem(
//    icon: @Composable BoxScope.() -> Unit,
//    text: @Composable BoxScope.() -> Unit,
//    selected: Boolean,
//    onSelected: () -> Unit,
//    animSpec: AnimationSpec<Float>,
//    modifier: Modifier = Modifier,
//) {
//
//    val animationProgress by animateFloatAsState(if (selected) 1f else 0f, animSpec)
//    YumBottomNavItemLayout(
//        icon = icon,
//        text = text,
//        animationProgress = animationProgress,
//        modifier = modifier
//            .selectable(selected = selected, onClick = onSelected)
//            .wrapContentSize()
//    )
//}
//
//@Composable
//private fun YumBottomNavItemLayout(
//    icon: @Composable BoxScope.() -> Unit,
//    text: @Composable BoxScope.() -> Unit,
//    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float,
//    modifier: Modifier = Modifier,
//) {
//    Layout(
//        modifier = modifier,
//        content = {
//            Box(
//                modifier = Modifier
//                    .layoutId("icon")
//                    .padding(horizontal = TextIconSpacing),
//                content = icon
//            )
//            val scale = lerp(0.6f, 1f, animationProgress)
//            Box(
//                modifier = Modifier
//                    .layoutId("text")
//                    .padding(horizontal = TextIconSpacing)
//                    .graphicsLayer {
//                        alpha = animationProgress
//                        scaleX = scale
//                        scaleY = scale
//                        transformOrigin = BottomNavLabelTransformOrigin
//                    },
//                content = text
//            )
//        }
//    ) { measurables, constraints ->
//        val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)
//        val textPlaceable = measurables.first { it.layoutId == "text" }.measure(constraints)
//
//        placeTextAndIcon(
//            textPlaceable,
//            iconPlaceable,
//            constraints.maxWidth,
//            constraints.maxHeight,
//            animationProgress
//        )
//    }
//}
//
//private fun MeasureScope.placeTextAndIcon(
//    textPlaceable: Placeable,
//    iconPlaceable: Placeable,
//    width: Int,
//    height: Int,
//    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float,
//): MeasureResult {
//    val iconY = (height - iconPlaceable.height) / 2
//    val textY = (height - textPlaceable.height) / 2
//
//    val textWidth = textPlaceable.width * animationProgress
//    val iconX = (width - textWidth - iconPlaceable.width) / 2
//    val textX = iconX + iconPlaceable.width
//
//    return layout(width, height) {
//        iconPlaceable.placeRelative(iconX.toInt(), iconY)
//        if (animationProgress != 0f) {
//            textPlaceable.placeRelative(textX.toInt(), textY)
//        }
//    }
//}
//
//@Composable
//private fun YumBottomNavIndicator(
//    strokeWidth: Dp = 2.dp,
//    color: Color = Color.Transparent,
//    shape: Shape = BottomNavIndicatorShape,
//) {
//    Spacer(
//        modifier = Modifier
//            .fillMaxSize()
//            .then(BottomNavigationItemPadding)
//            .clip(shape)
//            .background(MaterialTheme.colorScheme.background)
//            .border(strokeWidth, color, shape)
//    )
//}
//
//private val TextIconSpacing = 3.dp
//private val BottomNavHeight = 64.dp
//private val BottomNavLabelTransformOrigin = TransformOrigin(0f, 0.5f)
//private val BottomNavIndicatorShape = RoundedCornerShape(percent = 50)
//private val BottomNavigationItemPadding = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 16.dp)
