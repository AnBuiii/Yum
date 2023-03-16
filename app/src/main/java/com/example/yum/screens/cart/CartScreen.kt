package com.example.yum.screens.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.common.component.YumDivider
import com.example.yum.common.component.YumSurface
import com.example.yum.common.component.YumTabRow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


val cartTabList = listOf("Plan", "Shop", "Pantry")

@ExperimentalMaterial3Api
@Composable
fun CartScreen(
    onRecipeTap: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    YumSurface(modifier.fillMaxSize()) {
        CartBottomSheet(
            isOpen = uiState.isBottomSheetOpen,
            onChangeOpenBottomSheet = viewModel::onChangeBottomSheet
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // main screen
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CartTopBar(
                    selectedTab = uiState.tabState,
                    onTabChange = viewModel::onCartTabChange,
                    onMoreTap = {
                        viewModel.onChangeBottomSheet(true)
                    }
                )
                YumDivider(thickness = 2.dp)
                CartUnderTopBar()

            }

            // search appear


        }
    }
}

@Composable
private fun CartTopBar(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    onTabChange: (Int) -> Unit = {},
    onMoreTap: () -> Unit = {},
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        YumTabRow(
            selectedTab = selectedTab,
            onTabChange = onTabChange,
            tabList = cartTabList,
            modifier = Modifier.width(300.dp),
            textSize = 18.sp
        )
        IconButton(onClick = onMoreTap) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.size(26.dp)
            )
        }

    }
}

@ExperimentalMaterial3Api
@Composable
private fun CartBottomSheet(
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
            onDismissRequest = { onChangeOpenBottomSheet(false) },
            sheetState = modalBottomSheetState,
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
//                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                BottomBarItem(
                    imageVector = Icons.Default.Email,
                    text = "Email",
                    onTap = {
                        closeBottomSheet()
                    }
                )
                BottomBarItem(
                    imageVector = Icons.Default.Email,
                    text = "Email",
                    onTap = {
                        closeBottomSheet()
                    }
                )
                BottomBarItem(
                    imageVector = Icons.Default.Email,
                    text = "Email",
                    onTap = {
                        closeBottomSheet()
                    }
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
            .clickable { onTap() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(imageVector = imageVector, contentDescription = null)
            Text(text, fontSize = 16.sp)
        }
    }
}

@Composable
private fun CartUnderTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.clickable { },
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
            Text("Add to Shopping List")
        }
    }
}