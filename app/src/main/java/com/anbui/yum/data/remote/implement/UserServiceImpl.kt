package com.anbui.yum.data.remote.implement

import android.content.SharedPreferences
import android.util.Log
import com.anbui.yum.data.model.User
import com.anbui.yum.data.remote.service.SIGN_IN_URL
import com.anbui.yum.data.remote.service.SIGN_UP_URL
import com.anbui.yum.data.remote.service.UserService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class UserServiceImpl(
    private val prefs: SharedPreferences,
    private val client: HttpClient,
) : UserService {
    override val currentUser: User?
        get()  {
            val userId = prefs.getString("userId", null) ?: return null
            return User("123","123")
        }

    override suspend fun signIn(user: User) {
        try {
            val userId: String = client.post(SIGN_IN_URL) {
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body()
            Log.d("id", userId)
            prefs.edit()
                .putString("userId", userId)
                .apply()

        } catch (e: Exception) {
            Log.d("SIGNUP", e.toString())
        }
    }

    override suspend fun signUp(user: User) {
        try {
            val userId: String = client.post(SIGN_UP_URL) {
                contentType(ContentType.Application.Json)
                setBody(user)
            }.body()
            prefs.edit()
                .putString("userId", userId)
                .apply()


        } catch (e: Exception) {
            Log.d("SIGNUP", e.toString())
        }
    }


}