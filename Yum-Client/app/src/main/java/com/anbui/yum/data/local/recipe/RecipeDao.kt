package com.anbui.yum.data.local.recipe

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertAll(beers: List<RecipeEntity>)

    @Query("SELECT * FROM recipeentity")
    fun pagingSource(): PagingSource<Int, RecipeEntity>

    @Query("DELETE FROM recipeentity")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM recipeentity")
    suspend fun size(): Int

    @Query("SELECT * FROM recipeentity WHERE id = :recipeId LIMIT 1")
    suspend fun getRecipeById(recipeId: String): RecipeEntity?
}
