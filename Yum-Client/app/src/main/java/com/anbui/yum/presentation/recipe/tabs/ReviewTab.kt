package com.anbui.yum.presentation.recipe.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.domain.model.Review
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.presentation.recipe.component.ReviewItem
import com.anbui.yum.presentation.recipe.component.TabTopBar
import com.anbui.yum.ui.theme.YumGreen

@Composable
fun ReviewTab(
    reviews: List<Review>,
    getUserInfo: suspend (String) -> UserInfo,
    openReviewScreen: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        item {
            TabTopBar(
                modifier = Modifier.padding(24.dp),
                leading = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.clickable { openReviewScreen() },
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "",
                            tint = YumGreen,
                        )
                        Text(
                            "Live a review",
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                },
                trailing = {
                    FilledTonalButton(
                        onClick = {},
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = YumGreen.copy(alpha = 0.1f),
                            contentColor = YumGreen,
                        ),
                    ) {
                        Text("Sort")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            "",
                        )
                    }
                },
            )
        }

        items(
            reviews,
            key = {
                it.id
            }
        ) { review ->
            var a by remember { mutableStateOf(UserInfo()) }
            LaunchedEffect(true) {
                a = getUserInfo(review.userId)
            }

            ReviewItem(
                text = review.message,
                userImage = a.imageUrl,
                star = review.rating.toFloat(),
                userName = a.name,
//                navigate = navigate
            )
        }

    }
}
