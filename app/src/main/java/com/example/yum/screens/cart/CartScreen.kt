package com.example.yum.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CartScreen(
    onRecipeTap: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // main screen
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CartTopBar(selectedTab = uiState.tabState)
        }

        // search appear

    }
}

@Composable
private fun CartTopBar(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
//        Yum
    }
}