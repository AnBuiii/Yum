package com.anbui.yum.data.remote.ingredient

interface IngredientService {
    suspend fun search(query: String): List<IngredientDto>
    suspend fun getIngredientById(id: String): IngredientDto
}
