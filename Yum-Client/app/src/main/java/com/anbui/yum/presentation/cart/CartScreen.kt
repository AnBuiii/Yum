package com.anbui.yum.presentation.cart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.presentation.cart.components.CartBottomSheet
import com.anbui.yum.presentation.cart.components.CartTopBar
import com.anbui.yum.presentation.cart.tabs.PantryTab
import com.anbui.yum.presentation.cart.tabs.PlanTab
import com.anbui.yum.presentation.cart.tabs.ShopTab
import org.koin.androidx.compose.getViewModel


val cartTabList = listOf(
    "Plan",
    "Shop",
    "Pantry",
)

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun CartScreen(
    onRecipeTap: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = getViewModel(),
) {
    val uiState by viewModel.uiState
    val pagerState = rememberPagerState()
    val cartScreenBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

//    val a by viewModel.shoppingList.collectAsState()

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
            CartTopBar(onMoreTap = { viewModel.onChangeBottomSheet(true) }, pagerState = pagerState)
            YumDivider(
                thickness = 1.dp,
                color = Color.Black.copy(0.1f),
            )

            HorizontalPager(
                pageCount = 3,
                state = pagerState,
            ) { index ->
                when (index) {
                    0 -> PlanTab()
                    1 -> ShopTab(
                        hmItems = uiState.hmItems,
//                        hmItems = a,
                        onCheck = viewModel::check,
                        onRemove = viewModel::remove,
                    )

                    2 -> PantryTab()
                }
            }
//            CartUnderTopBar()

        }

        // search appear
    }
    LaunchedEffect(true){
        viewModel.getShoppingList()
    }
}






