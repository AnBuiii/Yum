package com.anbui.yum.presentation.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.FEED_SCREEN
import com.anbui.yum.SPLASH_SCREEN
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.local.user.UserEntity
import com.anbui.yum.data.remote.auth.AuthRequestDto
import com.anbui.yum.data.remote.auth.UserService
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch


class SplashViewModel(
    private val userService: UserService,
    private val yumDatabase: YumDatabase,
//    private val userInfoService: UserInfoService,
) : YumViewModel() {

    val showError = mutableStateOf(false)

    init {
        viewModelScope.launch {
            yumDatabase.userDao.clearUser()
            val a = userService.signIn(
                auth = AuthRequestDto(
                    username = "builehoaian",
                    password = "builehoaian",
                ),
            )
            yumDatabase.userDao.upsertAll(UserEntity(userId = a))
        }

    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
//        if(userService.currentUserId() != null){
//
//        }
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

        openAndPopUp(
            FEED_SCREEN,
            SPLASH_SCREEN,
        )
    }
}
