package com.anbui.yum.presentation.feed.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.R
import com.anbui.yum.ui.theme.YumBlack

@Composable
fun CollectionItem(
    onTap: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Max)
            .clickable {
                onTap("1")
            }


    ) {

        Image(
            painter = painterResource(id = R.drawable.food_1),
            contentDescription = "",
            modifier = Modifier.aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                ),
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(YumBlack)
                    .padding(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "",
                    tint = Color.White,
                )
            }
            Text(
                text = "Guided",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,

                    ),
            )
        }
    }
}
