package com.anbui.yum.data.remote.user_info

interface UserInfoService {
    suspend fun getUserInfo(userId: String): UserInfoDto?
}
