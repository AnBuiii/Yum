package com.anbui.yum.common.ext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.fieldModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp)
}

fun Modifier.buttonModifier(): Modifier {
    return this.padding(horizontal = 16.dp, vertical = 8.dp)
}

fun Modifier.bottomNavHeight(): Modifier{
    return this.height(56.dp)
}