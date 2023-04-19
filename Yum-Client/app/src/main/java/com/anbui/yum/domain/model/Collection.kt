package com.anbui.yum.domain.model

data class Collection(
    val name: String,
    val description: String,
    val recipes: List<String>,
    val userId: String,
    val id: String,
)
