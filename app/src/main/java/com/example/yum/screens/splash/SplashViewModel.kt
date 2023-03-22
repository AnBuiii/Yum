package com.example.yum.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.example.yum.FEED_SCREEN
import com.example.yum.SPLASH_SCREEN
import com.example.yum.model.recipes
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.ConfigurationService
import com.example.yum.model.service.LogService
import com.example.yum.model.service.RecipeService
import com.example.yum.screens.YumViewModel
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    private val recipeService: RecipeService,
    logService: LogService,
) : YumViewModel(logService) {

    val showError = mutableStateOf(false)

    init {
        launchCatching { configurationService.fetchConfiguration() }
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