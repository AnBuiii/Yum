package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 24.dp, end = 24.dp),
        ) {

            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
            FilledIconButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "")
            }
        }
    }

}
