package com.anbui.yum.data.local.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertAll(user: UserEntity)

    @Query("SELECT * FROM userentity")
    suspend fun getCurrentUser(): List<UserEntity>

    @Query("DELETE FROM userentity")
    suspend fun clearUser()
}
