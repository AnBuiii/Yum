package com.example.yum.common.snackbar

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

object SnackbarManager {
    private val messages: MutableStateFlow<SnackbarMessage?> = MutableStateFlow(null)
    val snackbarMessages: StateFlow<SnackbarMessage?>
        get() = messages.asStateFlow()

    fun showMessage(@StringRes message: Int) {
        messages.value = SnackbarMessage.ResourceSnackbar(message)
    }

    fun showMessage(message: SnackbarMessage) {
        messages.value = message
    }
}
