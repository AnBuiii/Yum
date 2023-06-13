package com.anbui.yum.presentation.other_user

import androidx.compose.runtime.mutableStateOf
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.mappers.toUserInfo
import com.anbui.yum.data.remote.auth.UserService
import com.anbui.yum.data.remote.collection.CollectionService
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.domain.model.Collection
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.presentation.YumViewModel


class OtherUserViewModel(
    private val userInfoService: UserInfoService,
    private val collectionService: CollectionService,
    private val userService: UserService,
    private val yumDatabase: YumDatabase,
//    val userService: UserService,
) : YumViewModel() {
    val uiState = mutableStateOf(OtherUserUiState())

    suspend fun init(userId: String) {
        uiState.value =
            uiState.value.copy(
                userInfo = userInfoService.getUserInfo(userId)?.toUserInfo() ?: UserInfo(),
                collections = collectionService.getCollectionByUserId(userId).map {
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

}
