package com.example.yum.screens.sign_in

import androidx.compose.runtime.mutableStateOf
import com.example.yum.SIGNIN_SCREEN
import com.example.yum.USER_SCREEN
import com.example.yum.common.ext.isValidEmail
import com.example.yum.common.ext.isValidPassword
import com.example.yum.common.snackbar.SnackbarManager
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.yum.R.string as AppText


@HiltViewModel
class SignInViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : YumViewModel(logService) {
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
        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(USER_SCREEN, SIGNIN_SCREEN)
        }
    }


}
