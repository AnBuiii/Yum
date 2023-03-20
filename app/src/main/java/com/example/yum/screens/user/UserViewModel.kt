package com.example.yum.screens.user

import com.example.yum.SIGNIN_SCREEN
import com.example.yum.SIGNUP_SCREEN
import com.example.yum.SPLASH_SCREEN
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import com.example.yum.screens.sign_up.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService,
) : YumViewModel(logService) {
    val uiState = accountService.currentUser.map { UserUiState(it.isAnonymous) }

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            restartApp(SPLASH_SCREEN)
            accountService.signOut()

        }
    }

    fun onSignInTap(openScreen: (String) -> Unit) = openScreen(SIGNIN_SCREEN)
    fun onSignUpTap(openScreen: (String) -> Unit) = openScreen(SIGNUP_SCREEN)

}