package com.example.yum.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.yum.screens.splash.SplashViewModel

@Composable
fun FeedRoute(
    onRecipeClick: (String) -> Unit,
){
    FeedScreen(onRecipeTap = {}, modifier = Modifier)
}

@Composable
fun FeedScreen(
    onRecipeTap: (Long) -> Unit,
    modifier: Modifier,
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Feed screen")
    }
}