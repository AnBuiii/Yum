package com.example.yum.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.yum.R

@Immutable
data class SearchCategoryCollection(
    val id: Long,
    val name: String,
    val categories: List<SearchCategory>
)

@Immutable
data class SearchCategory(
    val name: String,
    @DrawableRes val imageRes: Int
)

@Immutable
data class SearchSuggestionGroup(
    val id: Long,
    val name: String,
    val suggestions: List<String>
)

val searchCategoryCollections = listOf(
    SearchCategoryCollection(
        id = 0L,
        name = "Categories",
        categories = listOf(
            SearchCategory(
                name = "Chips & crackers",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Fruit snacks",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Desserts",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Nuts ",
                imageRes = R.drawable.food_1
            )
        )
    ),
    SearchCategoryCollection(
        id = 1L,
        name = "Lifestyles",
        categories = listOf(
            SearchCategory(
                name = "Organic",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Gluten Free",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Paleo",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Vegan",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Vegetarian",
                imageRes = R.drawable.food_1
            ),
            SearchCategory(
                name = "Whole30",
                imageRes = R.drawable.food_1
            )
        )
    )
)

val searchSuggestions = listOf(
    SearchSuggestionGroup(
        id = 0L,
        name = "Recent searches",
        suggestions = listOf(
            "Cheese",
            "Apple Sauce"
        )
    ),
    SearchSuggestionGroup(
        id = 1L,
        name = "Popular searches",
        suggestions = listOf(
            "Organic",
            "Gluten Free",
            "Paleo",
            "Vegan",
            "Vegetarian",
            "Whole30"
        )
    )
)