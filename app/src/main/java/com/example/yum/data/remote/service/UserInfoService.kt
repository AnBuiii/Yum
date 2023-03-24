package com.example.yum.data.remote.service

import com.example.yum.domain.model.UserInfo

interface UserInfoService {
    suspend fun getUserInfo(userId: String): UserInfo?

    suspend fun getCurrentUserInfo(): UserInfo?

    companion object {
//        const val
    }
}