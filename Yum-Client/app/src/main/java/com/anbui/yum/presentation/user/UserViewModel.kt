package com.anbui.yum.presentation.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.SIGNIN_SCREEN
import com.anbui.yum.SIGNUP_SCREEN
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.mappers.toUserInfo
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch


class UserViewModel(
    private val userInfoService: UserInfoService,
    private val yumDatabase: YumDatabase,
//    val userService: UserService,
) : YumViewModel() {
    val uiState = mutableStateOf(UserUiState())

    init {
        viewModelScope.launch {
            val hm = yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId
            if (hm != null) {
                uiState.value =
                    uiState.value.copy(
                        userInfo = userInfoService.getUserInfo(hm)!!.toUserInfo(),
                    )
            }
        }

    }

    fun logout() {
        viewModelScope.launch {
            yumDatabase.userDao.clearUser()
        }
    }

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
//            userInfoService.
//            restartApp(SPLASH_SCREEN)


        }
    }


    fun onSignInTap(openScreen: (String) -> Unit) = openScreen(SIGNIN_SCREEN)
    fun onSignUpTap(openScreen: (String) -> Unit) = openScreen(SIGNUP_SCREEN)


}
