package com.example.yum

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope



class YumAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

    val bottomBarTabs = HomeScreenSection.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val shouldShowBottomBar: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    val currentRoute: String?
        get() = navController.currentDestination?.route
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)
private tailrec fun findStartDestination(graph: NavDestination): NavDestination{
    return if(graph is NavGraph) findStartDestination(graph.startDestination!!) else  graph
}