package com.anbui.yum.presentation.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.common.component.YumRecipeCard
import com.anbui.yum.domain.model.Recipe

val recipes = listOf(
    Recipe(),
    Recipe(),
    Recipe(),
    Recipe(),
    Recipe(),
    Recipe(),
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2.75f),
        ) {
            Image(
                painter = painterResource(id = R.drawable.food_1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.5f))){}

            Text(
                text = "Collection",
                style = TextStyle(color = Color.White),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)

            )

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(24.dp)
            )
        }

        LazyColumn{
            items(recipes){recipe ->
                YumRecipeCard(recipe = recipe)
            }
        }


    }
}
