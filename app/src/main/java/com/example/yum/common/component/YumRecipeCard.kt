package com.example.yum.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yum.R

@ExperimentalMaterial3Api
@Composable
fun YumRecipeCard() {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.food_1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.2f)
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    ElevatedSuggestionChip(
                        onClick = { /* Do something! */ },
                        shape = RoundedCornerShape(50),
                        label = { Text("Yum Original") }
                    )
                    Text(
                        "Creamy Vegan Cauliflower Soup with Garlic",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    )
                }
                BadgedBox(
                    modifier = Modifier.clickable { },
                    badge = {
                        Badge {
                            val badgeNumber = "8"
                            Text(
                                badgeNumber,
                                modifier = Modifier.semantics {
                                    contentDescription = "$badgeNumber new notifications"
                                }
                            )
                        }
                    }) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}