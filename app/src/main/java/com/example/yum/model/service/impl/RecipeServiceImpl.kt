package com.example.yum.model.service.impl

import com.example.yum.model.Recipe
import com.example.yum.model.service.RecipeService
import com.example.yum.model.service.UserService
import com.example.yum.model.service.trace
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RecipeServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: UserService,
) :
    RecipeService {
    override val recipes: Flow<List<Recipe>>
        get() = auth.currentUser.flatMapLatest { user ->
            currentCollection(user.id).snapshots().map { snapshot ->
                snapshot.toObjects()
            }
        }

    override suspend fun getRecipe(recipeId: String): Recipe? =
        currentCollection(auth.currentUserId).document(recipeId).get().await().toObject()


    override suspend fun save(recipe: Recipe): String =
        trace(SAVE_RECIPE_TRACE) {
            currentCollection(auth.currentUserId).add(recipe).await().id
        }

    override suspend fun update(recipe: Recipe) {
        trace(UPDATE_RECIPE_TRACE) {
            currentCollection(auth.currentUserId).document(recipe.id).set(recipe).await()
        }
    }

    override suspend fun delete(recipeId: String) {
        currentCollection(auth.currentUserId).document(recipeId).delete().await()
    }

    override suspend fun deleteAllForUser(userId: String) {
        val matchingTasks = currentCollection(userId).get().await()
        matchingTasks.map { it.reference.delete().asDeferred() }.awaitAll()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(RECIPE_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val RECIPE_COLLECTION = "recipes"
        private const val SAVE_RECIPE_TRACE = "saveRecipe"
        private const val UPDATE_RECIPE_TRACE = "updateRecipe"
    }

}