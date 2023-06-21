package com.anbui.yum.presentation.user

import com.anbui.yum.domain.model.Collection
import com.anbui.yum.domain.model.UserInfo

data class UserUiState(
    val userInfo: UserInfo = UserInfo(),
    val isLoading: Boolean = false,
    val collections: List<Collection> = emptyList(),
    val isNewCollectionVisible: Boolean = false,
    val isChangeUsernameVisible: Boolean = false,
    val isChangeUserDescriptionVisible: Boolean = false,
    val onChangeUsername: String = "",
    val onChangeUserDescription: String = "",
)
