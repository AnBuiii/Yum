package com.anbui.yum.presentation.user

import com.anbui.yum.data.model.UserInfoDto

data class UserUiState(
    val userInfo : UserInfoDto = UserInfoDto(),
    val isLoading : Boolean = false
)