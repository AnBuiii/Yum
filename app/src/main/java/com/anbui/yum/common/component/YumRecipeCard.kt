package com.anbui.yum.common.component

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anbui.yum.R
import com.anbui.yum.data.model.Recipe

@ExperimentalMaterial3Api
@Composable
fun YumRecipeCard(
    onTap: () -> Unit = {},
    recipe: Recipe,
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clickable { onTap() },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.food_1),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.2f),
                        ),
                    ),
                ),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    ElevatedSuggestionChip(
                        onClick = { /* Do something! */ },
                        shape = RoundedCornerShape(50),
                        label = { Text("Yum Original") },
                    )
                    Text(
                        "Creamy Vegan Cauliflower Soup with Garlic",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
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
                                },
                            )
                        }
                    },
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Favorite",
                        tint = Color.White,
                    )
                }
            }
        }
    }
}
