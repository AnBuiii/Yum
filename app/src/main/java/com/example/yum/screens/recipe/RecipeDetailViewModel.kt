package com.example.yum.screens.recipe

import com.example.yum.model.service.LogService
import com.example.yum.model.service.RecipeService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    logService: LogService,
    private val recipeService: RecipeService
) : YumViewModel(logService) {

}