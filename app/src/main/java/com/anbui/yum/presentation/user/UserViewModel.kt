package com.anbui.yum.presentation.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.SIGNIN_SCREEN
import com.anbui.yum.SIGNUP_SCREEN
import com.anbui.yum.SPLASH_SCREEN
import com.anbui.yum.data.model.UserInfoDto
import com.anbui.yum.data.remote.service.UserInfoService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    userInfoService: UserInfoService

) : YumViewModel() {
    val uiState = mutableStateOf(UserUiState())

    init {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(userInfo = userInfoService.getCurrentUserInfo())
        }

    }

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            restartApp(SPLASH_SCREEN)

        }
    }



    fun onSignInTap(openScreen: (String) -> Unit) = openScreen(SIGNIN_SCREEN)
    fun onSignUpTap(openScreen: (String) -> Unit) = openScreen(SIGNUP_SCREEN)

}