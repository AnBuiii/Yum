package com.anbui.yum.presentation.splash

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.FEED_SCREEN
import com.anbui.yum.SPLASH_SCREEN
import com.anbui.yum.data.model.User
import com.anbui.yum.data.remote.service.UserInfoService
import com.anbui.yum.data.remote.service.UserService
import com.anbui.yum.presentation.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userService: UserService,
    private val userInfoService: UserInfoService,
) : YumViewModel() {

    val showError = mutableStateOf(false)

    init {
        val user = User("builehoaian", "builehoaian")
        viewModelScope.launch {
            userService.signIn(user)
        }

    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
//        if (!accountService.hasUser)
//            launchCatching(snackbar = false) {
//                try {
//                    accountService.createAnonymousUser()
//                } catch (ex: FirebaseAuthException) {
//                    showError.value = true
//                    throw ex
//                }
//
//            }
//
//        recipes.map {
//            it.copy(userId = accountService.currentUserId)
//        }.forEach {
//            launchCatching { recipeService.save(it) }
//        }

        openAndPopUp(FEED_SCREEN, SPLASH_SCREEN)
    }
}