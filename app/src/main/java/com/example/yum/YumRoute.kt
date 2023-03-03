package com.example.yum

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.yum.R.string as AppText

const val SPLASH_SCREEN = "SplashScreen"
const val HOME_SCREEN = "HomeScreen"

//home_route
const val FEED_SCREEN = "FeedScreen"
const val SEARCH_SCREEN = "SearchScreen"
const val CART_SCREEN = "CartScreen"
const val USER_SCREEN = "UserScreen"
const val RECIPE_SCREEN = "RecipeScreen"


const val RECIPE_ID = "recipeId"
const val RECIPE_DEFAULT_ID = "-1"
const val RECIPE_ID_ARG = "?$RECIPE_ID = {$RECIPE_ID}"

enum class HomeScreenSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    FEED(AppText.home_feed, Icons.Outlined.Home, FEED_SCREEN),
    SEARCH(AppText.home_search, Icons.Outlined.Home, FEED_SCREEN),
    CART(AppText.home_cart, Icons.Outlined.Home, FEED_SCREEN),
    USER(AppText.home_user, Icons.Outlined.Home, FEED_SCREEN),
}


