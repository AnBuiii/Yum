package com.example.yum.model


import com.google.firebase.firestore.DocumentId

data class Recipe(
    @DocumentId val id: String = "",
    val title: String = "",
)