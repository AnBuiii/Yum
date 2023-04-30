package com.anbui.yum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anbui.yum.data.local.recipe.RecipeDao
import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.data.local.recipe_remote_key.RecipeRemoteKey
import com.anbui.yum.data.local.recipe_remote_key.RecipeRemoteKeyDao
import com.anbui.yum.data.local.user.UserDao
import com.anbui.yum.data.local.user.UserEntity

@Database(
    entities = [
        RecipeEntity::class,
        RecipeRemoteKey::class,
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class YumDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    abstract val recipeRemoteDao: RecipeRemoteKeyDao
    abstract val userDao: UserDao
}
