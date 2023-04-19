package com.anbui.yum.presentation.cart.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.ui.theme.YumGreen

@Composable
fun YumIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textSize: TextUnit = 10.sp,
    iconSize: Dp = 18.dp,
    spacing: Dp = 0.dp,
    text: String,
    icon: ImageVector,

    ) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            spacing,
            Alignment.CenterVertically,
        ),
    ) {
        Icon(
            Icons.Default.Edit,
            "",
            modifier = Modifier.size(iconSize),
            tint = YumGreen,
        )
        Text(text, fontSize = textSize)
    }
}

@Composable
fun YumIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textSize: TextUnit = 10.sp,
    iconSize: Dp = 18.dp,
    spacing: Dp = 0.dp,
    text: String,
    icon: Int,

    ) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            spacing,
            Alignment.CenterVertically,
        ),
    ) {
        Icon(
            painterResource(id = icon),
            "",
            modifier = Modifier.size(iconSize),
            tint = YumGreen,
        )
        Text(text, fontSize = textSize)
    }
}
