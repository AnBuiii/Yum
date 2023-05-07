package com.anbui.yum.presentation.cart.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun CartBottomSheet(
    isOpen: Boolean = false,
    onChangeOpenBottomSheet: (Boolean) -> Unit = {},
    modalBottomSheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    fun closeBottomSheet() {
        coroutineScope.launch { modalBottomSheetState.hide() }
            .invokeOnCompletion {
                if (!modalBottomSheetState.isVisible) {
                    onChangeOpenBottomSheet(false)
                }
            }
    }
    if (isOpen) {
        ModalBottomSheet(
            onDismissRequest = {
                onChangeOpenBottomSheet(false)

            },
            sheetState = modalBottomSheetState,
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        bottom = 16.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
//                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                BottomBarItem(
                    imageVector = Icons.Default.Email,
                    text = "Email",
                    onTap = {
                        closeBottomSheet()
                    },
                )
                BottomBarItem(
                    imageVector = Icons.Default.Email,
                    text = "Email",
                    onTap = {
                        closeBottomSheet()
                    },
                )
                BottomBarItem(
                    imageVector = Icons.Default.Email,
                    text = "Email",
                    onTap = {
                        closeBottomSheet()
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    onTap: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTap() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 16.dp,
                )
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
            )
            Text(
                text,
                fontSize = 16.sp,
            )
        }
    }
}
