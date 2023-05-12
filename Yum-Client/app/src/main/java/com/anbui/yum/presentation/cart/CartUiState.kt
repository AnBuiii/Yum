package com.anbui.yum.presentation.cart

import com.anbui.yum.domain.model.ShoppingList

data class CartUiState(
    val tabState: Int = 0,
    val isBottomSheetOpen: Boolean = false,
    val hmItems: List<ShoppingList> = listOf()
)
