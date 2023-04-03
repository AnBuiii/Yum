package com.anbui.yum.presentation.user

import com.anbui.yum.data.model.UserInfo

data class UserUiState(
    val userInfo : UserInfo = UserInfo(),
    val isLoading : Boolean = false
)
