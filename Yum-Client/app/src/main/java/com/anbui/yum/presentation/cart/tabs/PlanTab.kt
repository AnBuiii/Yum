package com.anbui.yum.presentation.cart.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PlanTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            "Under constructor",
            modifier = Modifier
                .align(Alignment.Center),
        )
    }
}
