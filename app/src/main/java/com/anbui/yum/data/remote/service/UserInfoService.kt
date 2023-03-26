package com.anbui.yum.data.remote.service

import com.anbui.yum.data.model.UserInfoDto

interface UserInfoService {
    suspend fun getUserInfo(userId: String): UserInfoDto?

    suspend fun getCurrentUserInfo(): UserInfoDto

    companion object {
//        const val
    }
}