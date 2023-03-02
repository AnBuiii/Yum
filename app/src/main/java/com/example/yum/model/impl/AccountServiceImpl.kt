package com.example.yum.model.impl

import com.example.yum.model.Account
import com.example.yum.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {
    override val currentAccountId: String
        get() = TODO("Not yet implemented")
    override val hasAccount: Boolean
        get() = TODO("Not yet implemented")
    override val currentAccount: Flow<Account>
        get() = TODO("Not yet implemented")

    override suspend fun authenticate(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun sendRecoveryEmail(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createAnonymousAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun linkAccount(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}