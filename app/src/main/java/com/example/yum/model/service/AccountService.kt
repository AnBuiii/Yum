package com.example.yum.model.service

import com.example.yum.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentAccountId: String
    val hasAccount: Boolean

    val currentAccount: Flow<Account>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()


}