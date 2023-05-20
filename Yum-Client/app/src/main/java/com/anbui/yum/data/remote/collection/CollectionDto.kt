package com.anbui.yum.data.remote.collection

import com.anbui.yum.domain.model.Recipe
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDto(
    val title: String = "",
    val userId: String = "",
    val recipes: List<Recipe> = emptyList(),
    val id: String = "",
)
