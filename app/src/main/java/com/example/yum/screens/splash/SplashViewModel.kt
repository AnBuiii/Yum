package com.example.yum.screens.splash

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.yum.FEED_SCREEN
import com.example.yum.SPLASH_SCREEN
import com.example.yum.data.remote.service.UserInfoService
import com.example.yum.data.remote.service.UserService
import com.example.yum.domain.model.User
import com.example.yum.model.recipes
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.ConfigurationService
import com.example.yum.model.service.LogService
import com.example.yum.model.service.RecipeService
import com.example.yum.screens.YumViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    private val recipeService: RecipeService,
    logService: LogService,
    private val userService: UserService,
    private val userInfoService: UserInfoService,
) : YumViewModel(logService) {

    val showError = mutableStateOf(false)

    init {
        launchCatching {
            configurationService.fetchConfiguration()
        }
        val user = User("anan", "1231231232")
        viewModelScope.launch {
            userService.signIn(user)
            Log.d("HELLO",userInfoService.getCurrentUserInfo().toString())
        }

    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if (!accountService.hasUser)
            launchCatching(snackbar = false) {
                try {
                    accountService.createAnonymousUser()
                } catch (ex: FirebaseAuthException) {
                    showError.value = true
                    throw ex
                }

            }

        recipes.map {
            it.copy(userId = accountService.currentUserId)
        }.forEach {
            launchCatching { recipeService.save(it) }
        }

        openAndPopUp(FEED_SCREEN, SPLASH_SCREEN)
    }
}