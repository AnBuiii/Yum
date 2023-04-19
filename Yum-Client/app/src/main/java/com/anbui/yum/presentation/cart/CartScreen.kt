package com.anbui.yum.presentation.cart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.presentation.cart.components.CartBottomSheet
import com.anbui.yum.presentation.cart.components.CartTopBar
import com.anbui.yum.presentation.cart.tabs.PantryTab
import com.anbui.yum.presentation.cart.tabs.PlanTab
import com.anbui.yum.presentation.cart.tabs.ShopTab
import kotlinx.coroutines.launch


val cartTabList = listOf("Plan", "Shop", "Pantry")

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun CartScreen(
    onRecipeTap: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    val pagerState = rememberPagerState()
    val cartScreenBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    var hm by remember { mutableStateOf(false) }

    YumSurface(
        modifier.fillMaxSize(),
        color = Color.White,
    ) {
        CartBottomSheet(
            isOpen = uiState.isBottomSheetOpen,
            onChangeOpenBottomSheet = viewModel::onChangeBottomSheet,
        )
//        ModalBottomSheet(
//            sheetState = cartScreenBottomSheetState,
//            onDismissRequest = {
//                coroutineScope.launch { cartScreenBottomSheetState.hide() }
//
//            },
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//            ) {
//
//            }
//        }
        // main screen
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            CartTopBar(
                selectedTab = pagerState.currentPage,
                onTabChange = {
                    coroutineScope.launch { pagerState.animateScrollToPage(it) }
                },
                onMoreTap = { viewModel.onChangeBottomSheet(true) },
            )
            YumDivider(thickness = 3.dp)

            HorizontalPager(
                pageCount = 3,
                state = pagerState,
            ) { index ->
                when (index) {
                    0 -> PlanTab()
                    1 -> ShopTab(
                        hmItems = uiState.hmItems,
                        onCheck = { id ->
                            viewModel.remove(id)
                        },
                    )

                    2 -> PantryTab()
                }
            }
//            CartUnderTopBar()

        }

        // search appear
    }
}






