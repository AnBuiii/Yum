package com.example.yum.data.remote.service

import com.example.yum.domain.model.User
import com.example.yum.domain.model.UserInfo

interface UserService {
    suspend fun signUp(user: User)
    suspend fun signIn(user: User)
}