package com.example.yum.screens.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor() : ViewModel() {
    val uiState = mutableStateOf(UserUIState())


}