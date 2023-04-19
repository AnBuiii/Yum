package com.anbui.yum.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen

@Composable
fun CategoryHeaderItem(
    modifier: Modifier = Modifier,
    categoriesName: String = "",
    amount: Int = 0,
    isCollapsed: Boolean = false,
    onTab: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onTab() }
            .padding(16.dp),
    ) {
        Text(
            text = buildAnnotatedString {
                append("$categoriesName ")
                withStyle(style = SpanStyle(color = YumGreen)) {
                    append("($amount)")
                }
            },
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = YumBlack,

            )

        Icon(
            if (isCollapsed) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            tint = YumGreen
        )

    }
}
