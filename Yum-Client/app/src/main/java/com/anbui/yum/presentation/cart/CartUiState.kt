package com.anbui.yum.presentation.cart

import com.anbui.yum.domain.model.MealPlan
import com.anbui.yum.domain.model.ShoppingItem

data class CartUiState(
    val tabState: Int = 0,
    val isBottomSheetOpen: Boolean = false,
    val isShoppingItemBottomSheetOpen: Boolean = false,
    val onChangeShoppingItem: ShoppingItem = ShoppingItem(),
    val hmItems: List<ShoppingItem> = emptyList(),
    val mealPlans: List<MealPlan> = emptyList(),
    val isSearchOpen: Boolean = false,
    val isDatePickerDialogOpen: Boolean = false,
    val isTimePickerDialogOpen: Boolean = false,
    val selectedDateInLong: Long = 0L,
    val onChangeMealPlan: MealPlan = MealPlan()
)
