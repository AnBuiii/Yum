package com.anbui.yum.presentation.collection

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.mappers.toCollection
import com.anbui.yum.data.remote.collection.CollectionService
import com.anbui.yum.domain.model.Collection
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch

class CollectionViewModel(
    private val collectionService: CollectionService,
) : YumViewModel() {
    val uiState = mutableStateOf(CollectionUiState())
    fun getCollection(id: String) {
        try{
            viewModelScope.launch {
                uiState.value = uiState.value.copy(
                    collection = collectionService.getCollectionById(id).toCollection()
                )
            }
        } catch (e: Exception){
            uiState.value = uiState.value.copy(
                collection = Collection(name = "Unknown")
            )
        }
    }

    fun removeRecipe(recipeId : String){
        try{

        }
        catch (e: Exception){}

    }
}
