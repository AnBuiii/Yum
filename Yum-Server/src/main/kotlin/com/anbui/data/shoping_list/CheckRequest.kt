package com.anbui.data.shoping_list

import kotlinx.serialization.Serializable

@Serializable
data class CheckRequest(
    val id: String,
    val isChecked: Boolean
)
