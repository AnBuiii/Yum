package com.anbui.yum.data.local.meal_plan

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MealPlanDao {
    @Query("SELECT * FROM mealplanentity")
    fun getMealPlans(): List<MealPlanEntity>

    @Query("DELETE FROM mealplanentity")
    fun clearAll()

    @Upsert
    fun upsertAll(mealPlans: List<MealPlanEntity>)

}
