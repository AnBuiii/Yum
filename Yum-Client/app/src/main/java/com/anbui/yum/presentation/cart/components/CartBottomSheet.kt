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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.common.component.ListItemPicker
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
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
        coroutineScope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
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
                modifier = Modifier.padding(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemCartBottomSheet(
    shoppingList: ShoppingList,
    isOpen: Boolean = false,
    onSave: (Int, String) -> Unit,
    onChangeOpenBottomSheet: (Boolean) -> Unit = {},
    modalBottomSheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    fun closeBottomSheet() {
        coroutineScope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
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
            shape = RectangleShape,
            dragHandle = {},
            containerColor = Color.White,
        ) {
            var amount by remember { mutableStateOf(shoppingList.amount) }
            var unit by remember { mutableStateOf(shoppingList.unit) }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        bottom = 16.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
//                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = shoppingList.recipeName.uppercase(),
                        color = YumBlack.copy(0.5f),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                    Text(
                        text = "$amount $unit ${shoppingList.foodName}",
                        color = YumBlack,
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                    ) {
                        ListItemPicker(
                            value = amount,
                            onValueChange = { amount = it },
                            list = (0..10).toList(),
                            modifier = Modifier.weight(1f),
                            dividersColor = YumBlack,
                        )
                        ListItemPicker(
                            value = unit,
                            onValueChange = { unit = it },
                            list = units,
                            modifier = Modifier.weight(1f),
                            dividersColor = YumBlack,
                        )
                    }
                    Button(
                        onClick = {
                            onSave(
                                amount,
                                unit,
                            )
                            closeBottomSheet()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = YumGreen),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Save")
                    }

                }
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

private val units = listOf(
    "unit",
    "can",
    "pkg.",
    "bag",
    "btl.",
    "box",
    "block",
    "jar",
    "oz.",
    "lb.",
    "cup",
    "pt.",
    "qt.",
    "gal.",
)
