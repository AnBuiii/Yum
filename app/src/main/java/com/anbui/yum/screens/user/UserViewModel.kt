package com.anbui.yum.screens.user

import androidx.compose.runtime.mutableStateOf
import com.anbui.yum.SIGNIN_SCREEN
import com.anbui.yum.SIGNUP_SCREEN
import com.anbui.yum.SPLASH_SCREEN
import com.anbui.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
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