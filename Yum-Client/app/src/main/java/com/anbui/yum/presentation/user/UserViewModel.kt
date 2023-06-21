package com.anbui.yum.presentation.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.SIGNIN_SCREEN
import com.anbui.yum.SIGNUP_SCREEN
import com.anbui.yum.common.snackbar.SnackbarManager
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.local.user.UserEntity
import com.anbui.yum.data.mappers.toUserInfo
import com.anbui.yum.data.mappers.toUserInfoDto
import com.anbui.yum.data.remote.auth.AuthRequestDto
import com.anbui.yum.data.remote.auth.UserService
import com.anbui.yum.data.remote.collection.CollectionDto
import com.anbui.yum.data.remote.collection.CollectionService
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.domain.model.Collection
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch


class UserViewModel(
    private val userInfoService: UserInfoService,
    private val collectionService: CollectionService,
    private val userService: UserService,
    private val yumDatabase: YumDatabase,
//    val userService: UserService,
) : YumViewModel() {
    val uiState = mutableStateOf(UserUiState())

    fun init() {
        viewModelScope.launch {
            val userId = yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId
            if (userId != null) {
                getUserInfo(userId)
                getCurrentUserCollection()
            }
        }

    }

    private suspend fun getUserInfo(userId: String) {
        uiState.value =
            uiState.value.copy(
                userInfo = userInfoService.getUserInfo(userId)?.toUserInfo() ?: UserInfo(),
            )
    }

    private suspend fun getCurrentUserCollection() {
        uiState.value =
            uiState.value.copy(
                collections = collectionService.getCollectionByUserId(uiState.value.userInfo.userId)
                    .map {
                        Collection(
                            name = it.title,
                            userId = it.userId,
                            id = it.id,
                            recipes = it.recipes,
                            description = "",
                        )
                    },
            )
    }


    suspend fun login(email: String, password: String): String {
        val a = userService.signIn(
            auth = AuthRequestDto(
                username = email,
                password = password,
            ),
        )
        if (a.isNotEmpty()) {
            yumDatabase.userDao.clearUser()
            yumDatabase.userDao.upsertAll(UserEntity(userId = a))
        }
        return a
    }

    init {


    }

    fun logout() {
        viewModelScope.launch {
            yumDatabase.userDao.clearUser()
        }
    }

    fun onNewCollection(name: String) {
        viewModelScope.launch {
            collectionService.insertCollection(
                CollectionDto(
                    title = name,
                    userId = yumDatabase.userDao.getCurrentUser().first().userId,
                ),
            )
            getCurrentUserCollection()
        }
    }

    fun changeNewCollectionVisible(value: Boolean) {
        uiState.value = uiState.value.copy(isNewCollectionVisible = value)
    }

    fun changeChangeUsernameVisible(value: Boolean) {
        uiState.value = uiState.value.copy(isChangeUsernameVisible = value)
    }

    fun changeChangeUserDescriptionVisible(value: Boolean) {
        uiState.value = uiState.value.copy(isChangeUserDescriptionVisible = value)
    }

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
//            userInfoService.
//            restartApp(SPLASH_SCREEN)

        }
    }


    fun onSignInTap(openScreen: (String) -> Unit) = openScreen(SIGNIN_SCREEN)
    fun onSignUpTap(openScreen: (String) -> Unit) = openScreen(SIGNUP_SCREEN)
    fun changeUsername(value: String) {
        uiState.value = uiState.value.copy(onChangeUsername = value)
    }

    fun changeUserDescription(value: String) {
        uiState.value = uiState.value.copy(onChangeUserDescription = value)
    }

    fun changeUserInfo() {
        viewModelScope.launch {
            uiState.value.apply {
                if (onChangeUsername.isNotBlank() && onChangeUserDescription.isNotBlank()) {
                    Log.d("User info",userInfo.copy(
                        name = onChangeUsername,
                        title = onChangeUserDescription,
                    ).toUserInfoDto().toString() )
                    if (userInfoService.changeUserInfo(
                            userInfo.id,
                            userInfo.copy(
                                name = onChangeUsername,
                                title = onChangeUserDescription,
                            ).toUserInfoDto(),
                        )
                    ) {
                        getUserInfo(userInfo.userId)
                        SnackbarManager.showMessage("Change user info success")
                    }
                } else {
                    SnackbarManager.showMessage("User info invalid")
                }
            }
        }
    }


}
