package com.anbui.yum.data.remote.auth

import android.util.Log
import com.anbui.yum.common.util.Constants.SIGN_IN_URL
import com.anbui.yum.common.util.Constants.SIGN_UP_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class UserServiceImpl(
    private val client: HttpClient,
) : UserService {

    override suspend fun signIn(auth: AuthRequestDto): String {
        return try {
            client.post(SIGN_IN_URL) {
                contentType(ContentType.Application.Json)
                setBody(auth)
            }.body()

        } catch (e: Exception) {
            Log.d(
                "Sign in error",
                e.toString(),
            )
            ""
        }
    }

    override suspend fun signUp(auth: AuthRequestDto): String {
        return try {
            client.post(SIGN_UP_URL) {
                contentType(ContentType.Application.Json)
                setBody(auth)
            }.body()


        } catch (e: Exception) {
            Log.d(
                "Sign up error",
                e.toString(),
            )
            ""
        }
    }

}
