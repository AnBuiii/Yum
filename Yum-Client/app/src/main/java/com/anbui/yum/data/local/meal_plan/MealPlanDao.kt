package com.anbui.yum.data.local.meal_plan

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MealPlanDao {
    @Query("SELECT * FROM mealplanentity")
    suspend fun getMealPlans(): List<MealPlanEntity>

    @Query("DELETE FROM mealplanentity")
    suspend fun clearAll()

    @Upsert
    suspend fun upsert(mealPlan: MealPlanEntity)

    @Delete
    fun delete(mealPlan: MealPlanEntity)

}
