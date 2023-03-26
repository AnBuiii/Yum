package com.anbui.yum.data.remote.implement

import android.content.SharedPreferences
import android.util.Log
import com.anbui.yum.data.remote.dto.UserDto
import com.anbui.yum.data.remote.service.SIGN_IN_URL
import com.anbui.yum.data.remote.service.SIGN_UP_URL
import com.anbui.yum.data.remote.service.UserService
import com.anbui.yum.domain.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class UserServiceImpl(
    private val prefs: SharedPreferences,
    private val client: HttpClient,
) : UserService {

    override suspend fun signUp(user: User) {
        return try {
            val user: UserDto = client.post(SIGN_UP_URL) {
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body()


        } catch (e: Exception) {
            Log.d("SIGNUP", e.toString())
            return
        }
    }

    override suspend fun signIn(user: User) {
        return try {
            val user = client.post(SIGN_IN_URL) {
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body<UserDto>()

            prefs.edit()
                .putString("userId", user.id)
                .apply()

            Log.d("HELLO", user.toString())
            return
        } catch (e: Exception) {
            Log.d("SIGNIN", e.toString())
            prefs.edit()
                .putString("userId", null)
                .apply()
            return


        }
    }


}