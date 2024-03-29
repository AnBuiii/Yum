package com.anbui.yum.presentation.cart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewModelScope
import com.anbui.yum.common.alarm.AlarmItem
import com.anbui.yum.common.alarm.AlarmScheduler
import com.anbui.yum.common.notifcation.NotificationService
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.local.meal_plan.MealPlanEntity
import com.anbui.yum.data.mappers.toIngredient
import com.anbui.yum.data.mappers.toMealPlan
import com.anbui.yum.data.mappers.toMealPlanEntity
import com.anbui.yum.data.mappers.toShoppingItem
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.shopping_list.ShoppingItemSendDto
import com.anbui.yum.data.remote.shopping_list.ShoppingService
import com.anbui.yum.domain.model.Ingredient
import com.anbui.yum.presentation.YumViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone


class CartViewModel(
    private val shoppingService: ShoppingService,
    private val ingredientService: IngredientService,
    private val recipeService: RecipeService,
    private val yumDatabase: YumDatabase,
    private val scheduler: AlarmScheduler,
) : YumViewModel() {
    val uiState = mutableStateOf(CartUiState())


//    val shoppingList: StateFlow<List<ShoppingItem>> = flow {
//        while (true) {
//            val shoppingList = shoppingService.getShoppingList(
//                yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId ?: "",
//            )
//            emit(
//                shoppingList.map {
//                    val b = ingredientService.getIngredientById(it.ingredient)
//                    ShoppingItem(
//                        id = it.id,
//                        amount = it.amount.toInt(),
//                        foodName = b.name,
//                        recipeName = recipeService.getRecipe(it.recipe ?: "").title,
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
            val shoppingList = shoppingService.getShoppingList(
                yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId ?: "",
            )
            uiState.value = uiState.value.copy(
                hmItems = shoppingList.map {
                    it.toShoppingItem()
                },
            )
        }
    }

    fun getMealPlan() {
        val job = viewModelScope.launch {
            uiState.value = uiState.value.copy(
                mealPlans = yumDatabase.mealPlanDao.getMealPlans()
                    .map(MealPlanEntity::toMealPlan),
            )
        }


        uiState.value.mealPlans.filter { !it.isDone && it.time.isAfter(LocalDateTime.now())  }.forEach {
            val alarmItem = AlarmItem(
                time = it.time,
                message = it.title,
                id = it.notifyId
            )
            alarmItem.let(scheduler::schedule)
        }

    }

    fun check(id: String, value: Boolean) {
        uiState.value = uiState.value.copy(
            hmItems = uiState.value.hmItems.map {
                if (it.id == id) it.copy(isChecked = !it.isChecked)
                else it
            },
        )
        val a = uiState.value.hmItems.first { it.id == id }
        viewModelScope.launch {
            shoppingService.changeShoppingItemStatus(
                ShoppingItemSendDto(
                    id = id,
                    amount = a.amount.toDouble(),
                    unit = a.unit,
                    isChecked = value,
                ),
            )
        }
    }

    fun changeQuantity(id: String, amountValue: Int, unitValue: String) {
        uiState.value = uiState.value.copy(
            hmItems = uiState.value.hmItems.map {
                if (it.id == id) it.copy(
                    amount = amountValue,
                    unit = unitValue,
                )
                else it
            },
        )

        val a = uiState.value.hmItems.first { it.id == id }
        viewModelScope.launch {
            shoppingService.changeShoppingItemStatus(
                ShoppingItemSendDto(
                    id = id,
                    amount = amountValue.toDouble(),
                    unit = unitValue,
                    isChecked = a.isChecked,
                ),
            )
        }
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

    fun onChangeMealPlanDatePicker(value: Boolean) {
        uiState.value = uiState.value.copy(isDatePickerDialogOpen = value)
    }

    fun onChangeMealPlaneTimePicker(value: Boolean) {
        uiState.value = uiState.value.copy(isTimePickerDialogOpen = value)
    }

    fun onChangeSelectedDateInLong(value: Long) {
        uiState.value = uiState.value.copy(selectedDateInLong = value)
    }

    fun planCheck(id: String, isDone: Boolean) {
        uiState.value = uiState.value.copy(
            mealPlans = uiState.value.mealPlans.map {
                if (it.recipeId == id) it.copy(
                    isDone = isDone,
                )
                else it
            },
        )
        viewModelScope.launch {
            yumDatabase.mealPlanDao.upsert(
                uiState.value.mealPlans.first { it.recipeId == id }
                    .toMealPlanEntity(),
            )
        }
    }

    fun onChangeDateTimeSelectedPlan() {
        uiState.value = uiState.value.copy(
            mealPlans = uiState.value.mealPlans.map {
                if (it.recipeId == uiState.value.onChangeMealPlan.recipeId)
                    it.copy(
                        time = LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(uiState.value.selectedDateInLong),
                            TimeZone
                                .getDefault().toZoneId(),
                        ),
                    )
                else it
            },
        )
        uiState.value.mealPlans.filter { !it.isDone && it.time.isAfter(LocalDateTime.now())  }.forEach {
            val alarmItem = AlarmItem(
                time = it.time,
                message = it.title,
                id = it.notifyId
            )
            alarmItem.let(scheduler::schedule)
        }
        viewModelScope.launch {
            yumDatabase.mealPlanDao.upsert(
                uiState.value.mealPlans.first { it.recipeId == uiState.value.onChangeMealPlan.recipeId }
                    .toMealPlanEntity(),
            )
        }
//        getMealPlan()
    }


    fun remove(id: String) {
        viewModelScope.launch {
            shoppingService.removeShoppingItem(id)
        }
        uiState.value =
            uiState.value.copy(hmItems = uiState.value.hmItems.filterNot { it.id == id })
    }


    fun reload() {
        uiState.value = uiState.value
    }

    fun openBottomSheet(id: String) {
        uiState.value = uiState.value.copy(
            isShoppingItemBottomSheetOpen = true,
            onChangeShoppingItem = uiState.value.hmItems.first { it.id == id },
        )
    }

    fun onChangeSearch(value: Boolean) {
        uiState.value = uiState.value.copy(isSearchOpen = value)
    }

    fun openDatePickerDialog(id: String) {
        uiState.value = uiState.value.copy(
            isDatePickerDialogOpen = true,
            onChangeMealPlan = uiState.value.mealPlans.first { it.recipeId == id },
        )
    }

    // search

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _persons = MutableStateFlow(listOf<Ingredient>())

    @OptIn(FlowPreview::class)
    var persons = searchText
        .debounce(100)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { _, _ ->
            ingredientService.search(searchText.value).map { it.toIngredient() }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value,
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onAddIngredient(id: String) {
        viewModelScope.launch {
            val a = shoppingService.addShoppingItem(
                ShoppingItemSendDto(
                    yumDatabase.userDao.getCurrentUser().firstOrNull()?.userId ?: "",
                    ingredientId = id,
                    amount = 1.0,
                    isChecked = false,
                ),
            )
            if (a) {
                getShoppingList()
            }
        }


    }


}
