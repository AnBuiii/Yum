package com.example.yum.ui.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val searchNavigationRoute = "search_route"

fun NavHostController.navigateToSplash(navOptions: NavOptions? = null){
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(onRecipeClick: (String) -> Unit){
    composable(route = searchNavigationRoute){
        SearchRoute(onRecipeClick = onRecipeClick)
    }
}