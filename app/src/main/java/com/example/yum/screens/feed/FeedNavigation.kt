package com.example.yum.ui.feed

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.yum.ui.page.FeedRoute

const val feedNavigationRoute = "feed_route"

fun NavHostController.navigateToFeed(navOptions: NavOptions? = null){
    this.navigate(feedNavigationRoute, navOptions)
}

fun NavGraphBuilder.feedScreen(onRecipeClick: (String) -> Unit){
    composable(route = feedNavigationRoute){
        FeedRoute(onRecipeClick)
    }
}