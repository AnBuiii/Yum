package com.anbui.yum.data.remote.service

import com.anbui.yum.data.model.User

interface UserService {
    val currentUserId: String?
    suspend fun signIn(user: User)
    suspend fun signUp(user: User)

}