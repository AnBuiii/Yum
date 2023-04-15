package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.presentation.recipe.IMAGE_HEIGHT

@Composable
fun ImageItem(
    recipe: Recipe,
) {

    AsyncImage(
        model = recipe.imageUrl,
        contentDescription = "",
        modifier = Modifier
            .height(IMAGE_HEIGHT)
            .fillMaxWidth()
            .background(Color.Black),
        contentScale = ContentScale.FillBounds,
    )
}
