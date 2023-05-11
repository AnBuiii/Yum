package com.anbui.yum.presentation.cart

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.anbui.yum.presentation.YumViewModel


class CartViewModel (

) : YumViewModel() {
    val uiState = mutableStateOf(CartUiState())

    fun onCartTabChange(index: Int) {
        uiState.value = uiState.value.copy(tabState = index)
    }

    fun onChangeBottomSheet(value: Boolean) {
        uiState.value = uiState.value.copy(isBottomSheetOpen = value)
    }

    fun remove(id: String) {
        Log.d(
            "Remove",
            id,
        )
        uiState.value =
            uiState.value.copy(hmItems = uiState.value.hmItems.filterNot { it.id == id })
    }

    fun check(id: String) {
        uiState.value = uiState.value.copy(
            hmItems = uiState.value.hmItems.map {
                if (it.id == id) it.copy(isChecked = !it.isChecked)
                else it
            },
        )
    }

    fun reload() {
        uiState.value = uiState.value
    }

}
