package com.anbui.yum.common.component

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun YumDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Black.copy(0.1f),
    thickness: Dp = 1.dp,
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
    )
}

