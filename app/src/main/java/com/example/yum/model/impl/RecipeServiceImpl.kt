package com.example.yum.model.impl

import com.example.yum.model.Recipe
import com.example.yum.model.service.AccountService
import com.example.yum.model.service.RecipeService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService,
) :
    RecipeService {
    override val recipes: Flow<List<Recipe>>
        get() = TODO("Not yet implemented")

    override suspend fun getRecipe(recipeId: String): Recipe? {
        TODO("Not yet implemented")
    }

    override suspend fun save(recipe: Recipe): String {
        TODO("Not yet implemented")
    }

    override suspend fun update(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllForUser(userId: String) {
        TODO("Not yet implemented")
    }

}