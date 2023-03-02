package com.example.yum.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
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

    currentRoute: String,
    navigateToRoute: (String) -> Unit,
) {
    NavigationBar(
        contentColor = YumNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp
    ) {
//        val currentSection = tabs.first { it.route == currentRoute }
        val configuration = LocalConfiguration.current
        val currentLocale: Locale =
            ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault()

//        tabs.forEach { section ->
//            YumNavigationBarItem(
//                selected = section == currentSection,
//                onClick = { navigateToRoute(section.route) },
//                icon = {
//                    Icon(
//                        imageVector = section.icon,
//                        contentDescription = null,
//                    )
//                },
//                label = { Text(stringResource(section.title).uppercase(currentLocale)) },
//            )
//
//        }
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
