package com.anbui.yum.data.mappers

import com.anbui.yum.data.remote.shopping_list.ShoppingItemDto
import com.anbui.yum.domain.model.ShoppingItem

fun ShoppingItemDto.toShoppingItem(): ShoppingItem {
    return ShoppingItem(
        id = id,
        amount = amount.toInt(),
        unit = unit,
        isChecked = isChecked,
        categoriesName = ingredient.tag,
        recipeName = recipe?.title ?: "",
        foodName = ingredient.name,
    )
}
