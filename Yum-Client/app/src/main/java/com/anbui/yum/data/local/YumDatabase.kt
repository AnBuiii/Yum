package com.anbui.yum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anbui.yum.data.converters.LocalDateTimeConverter
import com.anbui.yum.data.local.meal_plan.MealPlanDao
import com.anbui.yum.data.local.meal_plan.MealPlanEntity
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
        MealPlanEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class YumDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    abstract val recipeRemoteDao: RecipeRemoteKeyDao
    abstract val userDao: UserDao
    abstract val mealPlanDao: MealPlanDao
}

