package com.anbui.yum

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.anbui.yum.R.string as AppText

const val SPLASH_SCREEN = "SplashScreen"
const val HOME_SCREEN = "HomeScreen"

//home_route
const val FEED_SCREEN = "FeedScreen"
const val SEARCH_SCREEN = "SearchScreen"
const val CART_SCREEN = "CartScreen"
const val USER_SCREEN = "UserScreen"
const val RECIPE_DETAIL_SCREEN = "RecipeScreen"
const val SIGNUP_SCREEN = "SignupScreen"
const val SIGNIN_SCREEN = "SignInScreen"
const val COLLECTION_SCREEN = "CollectionScreen"
const val REVIEW_SCREEN = "ReviewScreen"
const val OTHERUSER_SCREEN = "OtherUserScreen"


const val RECIPE_ID = "id"
const val RECIPE_DEFAULT_ID = "64228ff38bfb4c5001ed75b7"
const val RECIPE_ID_ARG = "?$RECIPE_ID = {$RECIPE_ID}"
const val COLLECTION_ID = "id"
const val COLLECTION_DEFAULT_ID = "-1"

enum class HomeScreenSection(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    FEED(
        AppText.home_feed,
        Icons.Outlined.Home,
        FEED_SCREEN,
    ),
    SEARCH(
        AppText.home_search,
        Icons.Outlined.Search,
        SEARCH_SCREEN,
    ),
    CART(
        AppText.home_cart,
        Icons.Outlined.ShoppingCart,
        CART_SCREEN,
    ),
    USER(
        AppText.home_user,
        Icons.Outlined.AccountCircle,
        USER_SCREEN,
    ),
}


