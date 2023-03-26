package com.anbui.yum.data.remote.service

import com.anbui.yum.domain.model.User

interface UserService {
    suspend fun signUp(user: User)
    suspend fun signIn(user: User)
}