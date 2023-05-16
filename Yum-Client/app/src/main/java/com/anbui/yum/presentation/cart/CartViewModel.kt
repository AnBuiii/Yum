package com.anbui.yum.presentation.cart

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.shopping_list.ShoppingService
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.launch


class CartViewModel(
    private val shoppingService: ShoppingService,
    private val ingredientService: IngredientService,
    private val recipeService: RecipeService,
    private val yumDatabase: YumDatabase,
) : YumViewModel() {
    val uiState = mutableStateOf(CartUiState())

//    val shoppingList: StateFlow<List<ShoppingList>> = flow {
//        while (true) {
//            val shoppingList = shoppingService.getShoppingList(
//                yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId ?: "",
//            )
//            emit(
//                shoppingList.map {
//                    val b = ingredientService.getIngredientById(it.ingredientId)
//                    ShoppingList(
//                        id = it.id,
//                        amount = it.amount.toInt(),
//                        foodName = b.name,
//                        recipeName = recipeService.getRecipe(it.recipeId ?: "").title,
//                        isChecked = it.isChecked,
//                        categoriesName = b.tag,
//                    )
//                },
//            )
//            delay(5000)
//
//        }
//    }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = listOf(),
//        )

    fun getShoppingList() {
        viewModelScope.launch {
            val a = shoppingService.getShoppingList(
                yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId ?: "",
            )
            uiState.value = uiState.value.copy(
                hmItems = a.map {
                    val b = ingredientService.getIngredientById(it.ingredientId)
                    ShoppingList(
                        id = it.id,
                        amount = it.amount.toInt(),
                        foodName = b.name,
                        recipeName = recipeService.getRecipe(it.recipeId ?: "").title,
                        isChecked = it.isChecked,
                        unit = it.unit,
                        categoriesName = b.tag,
                    )
                },
            )
        }
    }

    fun check(id: String, value: Boolean) {
        uiState.value = uiState.value.copy(
            hmItems = uiState.value.hmItems.map {
                if (it.id == id) it.copy(isChecked = !it.isChecked)
                else it
            },
        )
        viewModelScope.launch {
            shoppingService.changeShoppingItemStatus(
                id = id,
                isChecked = value,
            )
        }
    }

    fun edit(s: String) {
    }


    fun onCartTabChange(index: Int) {
        uiState.value = uiState.value.copy(tabState = index)
    }

    fun onChangeBottomSheet(value: Boolean) {
        uiState.value = uiState.value.copy(isBottomSheetOpen = value)
    }

    fun onChangeShoppingBottomSheet(value: Boolean) {
        uiState.value = uiState.value.copy(isShoppingItemBottomSheetOpen = value)
    }


    fun remove(id: String) {
        Log.d(
            "Remove",
            id,
        )
        uiState.value =
            uiState.value.copy(hmItems = uiState.value.hmItems.filterNot { it.id == id })
    }


    fun reload() {
        uiState.value = uiState.value
    }

    fun openBottomSheet(id: String) {
        uiState.value = uiState.value.copy(
            isShoppingItemBottomSheetOpen = true,
            onChangeShoppingList = uiState.value.hmItems.first { it.id == id },
        )
    }


}
