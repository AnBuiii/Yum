package com.example.yum.naviagtion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.yum.ui.feed.feedScreen
import com.example.yum.ui.search.searchNavigationRoute
import com.example.yum.ui.search.searchScreen
import com.example.yum.screens.splash.splashNavigationRoute
import com.example.yum.screens.splash.splashScreen

@Composable
fun YumNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = splashNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        splashScreen(navController)
        feedScreen(onRecipeClick = {})
        searchScreen(onRecipeClick = {})

//        cartScreen(onRecipeClick = {})
//        profileScreen()
    }
}