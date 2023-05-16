package com.anbui.yum.presentation.cart

import com.anbui.yum.domain.model.ShoppingList

data class CartUiState(
    val tabState: Int = 0,
    val isBottomSheetOpen: Boolean = false,
    val isShoppingItemBottomSheetOpen: Boolean = false,
    val onChangeShoppingList: ShoppingList = ShoppingList(),
    val hmItems: List<ShoppingList> = listOf()
)
