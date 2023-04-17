package com.anbui.yum.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.data.local.recipe_remote_key.RecipeRemoteKey
import com.anbui.yum.data.mappers.toRecipeEntity
import com.anbui.yum.data.remote.recipe.RecipeService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    private val yumDb: YumDatabase,
    private val recipeService: RecipeService,
) : RemoteMediator<Int, RecipeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)

                    remoteKeys?.nextPage?.minus(1) ?: 1
                    Log.d("asd", remoteKeys.toString())
                    remoteKeys


                    1
                }

                LoadType.PREPEND -> {
                    Log.d("a", "asd")
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null,
                        )
                    nextPage

//                    return MediatorResult.Success(
//                        endOfPaginationReached = true,
//                    )
                }

                LoadType.APPEND -> {
                    Log.d("Mediator", "append")

                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null,
                        )
                    nextPage

//                    val lastItem = state.lastItemOrNull()
//                    if(lastItem == null) {
//                        return MediatorResult.Success(endOfPaginationReached = true)
//                    } else {
//                        (yumDb.recipeDao.size() / state.config.pageSize) + 1
//                    }
                }
            }

            val recipes = recipeService.getRecipes(
                page = loadKey,
                pageCount = state.config.pageSize,
            )

            val endOfPaginationReached = recipes.isEmpty()

            val prevPage = if (loadKey == 1) null else loadKey - 1
            val nextPage = if (endOfPaginationReached) null else loadKey + 1

            yumDb.withTransaction {
//                if(loadType == LoadType.REFRESH) {
//                    yumDb.recipeDao.clearAll()
//                }
//                val recipeEntities = recipes.map { it.toRecipeEntity() }
//                yumDb.recipeDao.upsertAll(recipeEntities)

                if (loadType == LoadType.REFRESH) {
                    yumDb.recipeDao.clearAll()
                    yumDb.recipeRemoteDao.deleteAllRemoteKeys()
                }
                val keys = recipes.map { unsplashImage ->
                    RecipeRemoteKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage,
                    )
                }

                val recipeEntities = recipes.map { it.toRecipeEntity() }
                yumDb.recipeDao.upsertAll(recipeEntities)
                yumDb.recipeRemoteDao.addAllRemoteKeys(keys)
            }

            MediatorResult.Success(
                endOfPaginationReached = recipes.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RecipeEntity>,
    ): RecipeRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                yumDb.recipeRemoteDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, RecipeEntity>,
    ): RecipeRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                yumDb.recipeRemoteDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, RecipeEntity>,
    ): RecipeRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                yumDb.recipeRemoteDao.getRemoteKeys(id = unsplashImage.id)
            }
    }


}
