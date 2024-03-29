package com.anbui.yum

import android.content.res.Resources
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anbui.yum.common.component.YumBottomBar
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.common.snackbar.SnackbarManager
import com.anbui.yum.presentation.cart.CartScreen
import com.anbui.yum.presentation.category.CategoryScreen
import com.anbui.yum.presentation.collection.CollectionScreen
import com.anbui.yum.presentation.feed.FeedScreen
import com.anbui.yum.presentation.other_user.OtherUserScreen
import com.anbui.yum.presentation.recipe.RecipeDetailScreen
import com.anbui.yum.presentation.review.ReviewScreen
import com.anbui.yum.presentation.search.SearchScreen
import com.anbui.yum.presentation.sign_in.SignInScreen
import com.anbui.yum.presentation.splash.SplashScreen
import com.anbui.yum.presentation.user.UserScreen
import com.anbui.yum.ui.theme.YumTheme
import kotlinx.coroutines.CoroutineScope

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YumApp() {
    val appState = rememberYumAppState()
    YumTheme {
        YumSurface(
            color = MaterialTheme.colorScheme.background,
        ) {
            Scaffold(
                modifier = Modifier,
                topBar = {

                }, //TODO
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        YumBottomBar(
                            tabs = appState.bottomBarTabs,
                            currentRoute = appState.currentRoute!!,
                            navigateToRoute = appState::navigateToBottomBarRoute,
                        )
                    }
                    AnimatedVisibility(appState.shouldShowBottomBar) {

                    }
                },
                snackbarHost = { SnackbarHost(appState.snackbarHostState) },
            ) { paddingValues ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(paddingValues),
                ) {
                    yumGraph(appState)
                }

            }
        }

    }
}


// app state
@Composable
fun rememberYumAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
) = remember(
    navController,
    coroutineScope,
    snackbarManager,
) {
    YumAppState(
        navController,
        snackbarHostState,
        snackbarManager,
        resources,
        coroutineScope,
    )
}

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

// app graph
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.yumGraph(appState: YumAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(
            openAndPopUp = { route, popup ->
                appState.navigateAndPopUp(
                    route,
                    popup,
                )
            },
        )
    }

    composable(HomeScreenSection.FEED.route) {
        FeedScreen(
            onRecipeTap = { route ->
                appState.navigate(route)
            },
            onCollectionTab = { id ->
                appState.navigate("$COLLECTION_SCREEN/$id")
            },
            onCategoryTap = { categoryName ->
                appState.navigate("$CATEGORY_SCREEN/$categoryName")
            },

            )
        Log.d(
            "TAB LOG",
            appState.currentRoute!!,
        )

    }

    composable(HomeScreenSection.SEARCH.route) {
        SearchScreen(
            onRecipeTap = { id ->
                appState.navigate("$RECIPE_DETAIL_SCREEN/$id")
            },
        )
    }

    composable(HomeScreenSection.CART.route) {
        CartScreen(
            onRecipeTap = { id ->
                appState.navigate("$RECIPE_DETAIL_SCREEN/$id")
            },
        )
    }

    composable(HomeScreenSection.USER.route) {
        UserScreen(
            onOpenScreen = { route -> appState.navigate(route) },
            restartApp = appState::restartNavigate,
            onCollectionTab = { id ->
                appState.navigate("$COLLECTION_SCREEN/$id")
            },
        )
    }

    composable(SIGNIN_SCREEN) {
        SignInScreen(openAndPopUp = appState::navigateAndPopUp)
    }


    composable(
        route = "$RECIPE_DETAIL_SCREEN/{$RECIPE_ID}",
        arguments = listOf(
            navArgument(RECIPE_ID) {
                defaultValue = RECIPE_DEFAULT_ID
            },
        ),
    ) {
        RecipeDetailScreen(
            popUp = { appState.popUp() },
            openScreen = appState::navigate,
            recipeId = it.arguments?.getString(RECIPE_ID) ?: RECIPE_DEFAULT_ID,
        )
    }

    composable(
        route = "$COLLECTION_SCREEN/{id}",
        arguments = listOf(
            navArgument("id") {
                defaultValue = COLLECTION_DEFAULT_ID
            },
        ),
    ) {
        CollectionScreen(
            collectionId = it.arguments?.getString(COLLECTION_ID) ?: COLLECTION_DEFAULT_ID,
            onBack = appState::popUp,
            onRecipeTap = { id ->
                appState.navigate("$RECIPE_DETAIL_SCREEN/$id")
            },
        )
    }

    composable(
        route = "$REVIEW_SCREEN/{id}",
        arguments = listOf(
            navArgument("id") {
                defaultValue = RECIPE_DEFAULT_ID
            },
        ),
    ) {
        ReviewScreen(
            recipeId = it.arguments?.getString("id") ?: RECIPE_DEFAULT_ID,
            onBack = appState::popUp,
        )
    }

    composable(
        route = "$OTHERUSER_SCREEN/{id}",
        arguments = listOf(
            navArgument("id") {
                defaultValue = RECIPE_DEFAULT_ID
            },
        ),
    ) {
        OtherUserScreen(
            userId = it.arguments?.getString("id") ?: RECIPE_DEFAULT_ID,
            onCollectionTab = { id ->
                appState.navigate("$COLLECTION_SCREEN/$id")
            },
        )
    }

    composable(
        route = "$CATEGORY_SCREEN/{category_name}",
        arguments = listOf(
            navArgument("category_name") {
                defaultValue = ""
            },
        ),
    ) {
        CategoryScreen(
            categoryName = it.arguments?.getString("category_name") ?: "",
            onRecipeTap = { id ->
                appState.navigate("$RECIPE_DETAIL_SCREEN/$id")
            },
        )
    }
}


