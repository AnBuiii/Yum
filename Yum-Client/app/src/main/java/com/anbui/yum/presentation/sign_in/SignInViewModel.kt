package com.anbui.yum.presentation.sign_in

import androidx.compose.runtime.mutableStateOf
import com.anbui.yum.common.ext.isValidEmail
import com.anbui.yum.common.ext.isValidPassword
import com.anbui.yum.common.snackbar.SnackbarManager
import com.anbui.yum.presentation.YumViewModel
import com.anbui.yum.R.string as AppText


class SignInViewModel() : YumViewModel() {
    var uiState = mutableStateOf(SignInUiState())
    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
        }
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
        }
//        launchCatching {
//            accountService.authenticate(email, password)
//            openAndPopUp(USER_SCREEN, SIGNIN_SCREEN)
//        }
    }


}
