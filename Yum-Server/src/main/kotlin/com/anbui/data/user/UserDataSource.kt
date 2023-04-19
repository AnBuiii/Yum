package com.anbui.data.user

interface UserDataSource {
    suspend fun getUserByUsername(username: String): User?

    suspend fun getUserById(id: String): User?

    suspend fun insertUser(user: User): Boolean

    suspend fun getALlUser() : List<User>
}