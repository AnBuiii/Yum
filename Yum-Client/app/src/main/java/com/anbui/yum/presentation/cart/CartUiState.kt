package com.anbui.yum.presentation.cart

import com.anbui.yum.domain.model.ShoppingItem

data class CartUiState(
    val tabState: Int = 0,
    val isBottomSheetOpen: Boolean = false,
    val isShoppingItemBottomSheetOpen: Boolean = false,
    val onChangeShoppingItem: ShoppingItem = ShoppingItem(),
    val hmItems: List<ShoppingItem> = listOf(),
    val isSearchOpen: Boolean = false
)
