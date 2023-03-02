package com.example.yum.model.service

import com.example.yum.model.User
import kotlinx.coroutines.flow.Flow

interface UserService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousUser()
    suspend fun linkUser(email: String, password: String)
    suspend fun deleteUser()
    suspend fun signOut()


}