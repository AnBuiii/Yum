package com.example.yum.data.remote.implement

import android.content.SharedPreferences
import android.util.Log
import com.example.yum.data.remote.dto.UserInfoDto
import com.example.yum.data.remote.service.BASE_URL
import com.example.yum.data.remote.service.UserInfoService
import com.example.yum.domain.model.UserInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserInfoServiceImpl(
    private val prefs: SharedPreferences,
    private val client: HttpClient,
) : UserInfoService {
    override suspend fun getUserInfo(userId: String): UserInfo? {
//        val userId = prefs.getString("userId", null) ?: return null
        val userInfo = client.post("$BASE_URL/$userId/userInfo").body<UserInfoDto>().toUserInfo()
        Log.d("HELLO", userInfo.toString())
        return userInfo
    }

    override suspend fun getCurrentUserInfo(): UserInfo? {
        val userId = prefs.getString("userId", null) ?: return null
        return try{
            val userInfo = client.get("$BASE_URL/user/$userId/userInfo") {
            }.body<UserInfoDto>()

            println(userInfo)
            userInfo.toUserInfo()
        } catch (e: Exception){
            null
        }
    }
}