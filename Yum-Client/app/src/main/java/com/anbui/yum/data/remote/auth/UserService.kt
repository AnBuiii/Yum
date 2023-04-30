package com.anbui.yum.data.remote.auth

interface UserService {
    //    fun currentUserId(): String?
    suspend fun signIn(auth: AuthRequestDto): String
    suspend fun signUp(auth: AuthRequestDto): String

}
