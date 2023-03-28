package com.anbui.yum.data.remote.implement

import android.content.SharedPreferences
import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import com.anbui.yum.data.model.UserInfoDto
import com.anbui.yum.data.remote.service.UserInfoService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserInfoServiceImpl(
    private val prefs: SharedPreferences,
    private val client: HttpClient,
) : UserInfoService {

    override suspend fun getUserInfo(userId: String): UserInfoDto? {
        val userInfo = client.post("$BASE_URL/$userId/userInfo").body<UserInfoDto>()
        Log.d("HELLO", userInfo.toString())
        return userInfo
    }

    override suspend fun getCurrentUserInfo(): UserInfoDto {
        val userId = prefs.getString("userId", null) ?: return UserInfoDto()
        return try {
            val userInfo = client.get("$BASE_URL/user/$userId/userInfo") {
            }.body<UserInfoDto>()
            userInfo
        } catch (e: Exception) {
            UserInfoDto()
        }
    }
}
