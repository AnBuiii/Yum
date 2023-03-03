package com.example.yum

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.yum.component.YumBottomBar
import com.example.yum.screens.splash.SplashScreen
import com.example.yum.screens.feed.FeedScreen
import com.example.yum.ui.search.SearchScreen
import com.example.yum.ui.theme.YumTheme
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YumApp() {
    val appState = rememberYumAppState()
    val snackbarHostState = remember { SnackbarHostState() }
    YumTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            Scaffold(
                topBar = { }, //TODO
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        YumBottomBar(
                            tabs = appState.bottomBarTabs,
                            currentRoute = appState.currentRoute!!,
                            navigateToRoute = appState::navigate,
                        )
                    }
                },
                snackbarHost = { SnackbarHost(snackbarHostState) },
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
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(navController, coroutineScope) {
    YumAppState(navController, coroutineScope)
}

// app graph
fun NavGraphBuilder.yumGraph(appState: YumAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(
            openAndPopUp = { route, popup -> appState.navigateAndPopUp(route, popup) },
        )
    }

    navigation(
        route = HOME_SCREEN,
        startDestination = HomeScreenSection.FEED.route,
    ) {
        composable(HomeScreenSection.FEED.route) {
            FeedScreen(onRecipeTap = { })
        }
        composable(HomeScreenSection.SEARCH.route){
            SearchScreen(onRecipeClick = {})
        }
        composable(HomeScreenSection.CART.route){
            
        }
        composable(HomeScreenSection.USER.route){
            
        }

    }


    composable(
        route = "$RECIPE_SCREEN$RECIPE_ID_ARG",
        arguments = listOf(
            navArgument(RECIPE_ID) {
                defaultValue = RECIPE_DEFAULT_ID
            },
        ),
    ) {
        // recipe screen
    }
}

// home graph

fun NavGraphBuilder.homeGraph(appState: YumAppState) {

}

//fun NavGraphBuilder.
@Preview
@Composable
fun YumAppPreView() {
    YumApp()
}
