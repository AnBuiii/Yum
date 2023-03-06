package com.example.yum.screens.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.yum.HOME_SCREEN
import com.example.yum.SPLASH_SCREEN
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.acos

@HiltViewModel
class UserViewModel @Inject constructor(
    private val accountService: AccountService,
    private val logService: LogService
) : YumViewModel(logService) {
    val uiState = accountService.currentUser.map { UserUiState(it.isAnonymous) }

    fun onSignOutClick(restartApp: (String) -> Unit){
        launchCatching {
            restartApp(SPLASH_SCREEN)
            accountService.signOut()

        }
    }

}