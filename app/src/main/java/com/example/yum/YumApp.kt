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
                topBar = { TODO() },
                bottomBar = { TODO() },
                snackbarHost = { SnackbarHost(snackbarHostState) },
            ) { paddingValues ->
                NavHost(
                    navController = appState.navController,
                    startDestination = "SPLASH_SCREEN",
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

//resource

// app graph

fun NavGraphBuilder.yumGraph(appState: YumAppState) {
    composable(SPLASH_SCREEN) {

    }
}

//fun NavGraphBuilder.
@Preview
@Composable
fun YumAppPreView() {
    YumApp()
}
