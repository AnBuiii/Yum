package com.example.yum.screens.cart

import androidx.compose.runtime.mutableStateOf
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    logService: LogService,
) : YumViewModel(logService) {
    val uiState = mutableStateOf(CardUiState())

    fun onCartTabChange(index: Int) {
        uiState.value = uiState.value.copy(tabState = index)
    }

    fun onChangeBottomSheet(value: Boolean) {
        uiState.value = uiState.value.copy(isBottomSheetOpen = value)
    }

}