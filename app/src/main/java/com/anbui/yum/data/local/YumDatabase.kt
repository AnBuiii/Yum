package com.anbui.yum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anbui.yum.data.local.recipe.RecipeDao
import com.anbui.yum.data.local.recipe.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class YumDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
}
