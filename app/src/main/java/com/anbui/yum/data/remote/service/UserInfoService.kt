package com.anbui.yum.data.remote.service

import com.anbui.yum.domain.model.UserInfo

interface UserInfoService {
    suspend fun getUserInfo(userId: String): UserInfo?

    suspend fun getCurrentUserInfo(): UserInfo?

    companion object {
//        const val
    }
}