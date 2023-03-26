package com.anbui.yum.presentation.user

import androidx.compose.runtime.mutableStateOf
import com.anbui.yum.SIGNIN_SCREEN
import com.anbui.yum.SIGNUP_SCREEN
import com.anbui.yum.SPLASH_SCREEN
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(

) : YumViewModel() {
    val uiState = mutableStateOf(UserUiState())

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            restartApp(SPLASH_SCREEN)

        }
    }

    fun onSignInTap(openScreen: (String) -> Unit) = openScreen(SIGNIN_SCREEN)
    fun onSignUpTap(openScreen: (String) -> Unit) = openScreen(SIGNUP_SCREEN)

}