package com.example.yum.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import com.example.yum.SIGNUP_SCREEN
import com.example.yum.SPLASH_SCREEN
import com.example.yum.common.ext.isValidEmail
import com.example.yum.common.ext.isValidPassword
import com.example.yum.common.ext.passwordMatches
import com.example.yum.common.snackbar.SnackbarManager
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.yum.R.string as AppText


@HiltViewModel
class SignUpViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : YumViewModel(logService) {
    val uiState = mutableStateOf(SignUpUiState())

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    private val repeatPassword
        get() = uiState.value.repeatPassword

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUp(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
        }

        if (!password.passwordMatches(repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
        }

        launchCatching {
            accountService.linkAccount(email, password)
            openAndPopUp(SPLASH_SCREEN, SIGNUP_SCREEN)
        }

    }

}