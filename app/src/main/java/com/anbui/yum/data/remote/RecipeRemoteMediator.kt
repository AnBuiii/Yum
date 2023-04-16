package com.anbui.yum.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.data.mappers.toRecipeEntity
import com.anbui.yum.data.remote.recipe.RecipeService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    private val yumDb: YumDatabase,
    private val recipeService: RecipeService
): RemoteMediator<Int, RecipeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    } else {
                        (yumDb.recipeDao.size() / state.config.pageSize) + 1
                    }
                }
            }

            val recipes = recipeService.getRecipes(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            yumDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    yumDb.recipeDao.clearAll()
                }
                val recipeEntities = recipes.map { it.toRecipeEntity() }
                yumDb.recipeDao.upsertAll(recipeEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = recipes.size < state.config.pageSize
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
