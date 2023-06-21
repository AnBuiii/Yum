package com.anbui.data.user_info

interface UserInfoDataSource {
    suspend fun getUserInfoById(id: String): UserInfo?

    suspend fun getUserInfoByUserId(id: String): UserInfo?

    suspend fun insertUserInfo(userInfo: UserInfo): Boolean

    suspend fun updateUserInfoById(id: String, userInfo: UserInfo): Boolean

    suspend fun getAllUserInfo(): List<UserInfo>

}
