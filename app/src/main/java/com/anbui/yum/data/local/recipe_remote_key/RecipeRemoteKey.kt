package com.anbui.yum.data.local.recipe_remote_key

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
