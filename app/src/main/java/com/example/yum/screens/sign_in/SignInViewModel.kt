package com.example.yum.screens.sign_in

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.yum.SIGNIN_SCREEN
import com.example.yum.USER_SCREEN
import com.example.yum.common.snackbar.SnackbarManager
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject
import com.example.yum.R.string as AppText

private const val MIN_PASS_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
@HiltViewModel
class SignInViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : YumViewModel(logService) {
    var uiState = mutableStateOf(SignInUIState())
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
        if(!password.isValidPassword()){
            SnackbarManager.showMessage(AppText.password_error)
        }
        launchCatching {
            Log.d("auth", "$email $password")
            accountService.authenticate("builehoaiannn2002@gmail.com", "Password123@1")
            openAndPopUp(USER_SCREEN, SIGNIN_SCREEN)
        }

//        openAndPopUp(USER_SCREEN, SIGNIN_SCREEN)
    }



    fun String.isValidPassword(): Boolean{
        return this.isNotBlank() &&
                this.length >= MIN_PASS_LENGTH &&
                Pattern.compile(PASS_PATTERN).matcher(this).matches()
    }
}
