package com.anbui.yum.data.remote.user_info

import android.util.Log
import com.anbui.yum.common.util.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UserInfoServiceImpl(
    private val client: HttpClient,
) : UserInfoService {

    override suspend fun getUserInfo(userId: String): UserInfoDto? {
        return try {
            client.get("$BASE_URL/user/$userId/userInfo").body<UserInfoDto>()
        } catch (e: Exception) {
            Log.d(
                "get user info error",
                e.toString(),
            )
            null
        }
    }
}
