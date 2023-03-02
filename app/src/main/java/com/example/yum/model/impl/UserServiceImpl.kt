package com.example.yum.model.impl

import com.example.yum.model.User
import com.example.yum.model.service.UserService
import com.example.yum.model.service.trace
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val auth: FirebaseAuth) : UserService {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun createAnonymousUser() {
        auth.signInAnonymously().await()
    }

    override suspend fun linkUser(email: String, password: String) {
        trace(LINK_ACCOUNT_TRACE) {
            val credential = EmailAuthProvider.getCredential(email, password)
            auth.currentUser!!.linkWithCredential(credential).await()
        }
    }

    override suspend fun deleteUser() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        if(auth.currentUser!!.isAnonymous){
            auth.currentUser!!.delete()
        }
        auth.signOut()

        createAnonymousUser()
    }

    companion object {
        private const val LINK_ACCOUNT_TRACE = "linkAccount"
    }
}