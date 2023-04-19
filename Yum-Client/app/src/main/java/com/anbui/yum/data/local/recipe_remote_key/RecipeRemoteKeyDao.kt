package com.anbui.yum.data.local.recipe_remote_key

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeRemoteKeyDao {

    @Query("SELECT * FROM reciperemotekey WHERE id =:id")
    suspend fun getRemoteKeys(id: String): RecipeRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RecipeRemoteKey>)

    @Query("DELETE FROM reciperemotekey")
    suspend fun deleteAllRemoteKeys()

}
