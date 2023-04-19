package com.anbui.yum.presentation.recipe.component

//import androidx.compose.material.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.normal
import com.anbui.yum.ui.theme.semi

@Composable
fun ReviewItem(
    userImage: String = "",
    userName: String = "User name",
    time: String = "2 day ago",
    text: String = "It could be good and it could be bad at once",
    star: Float = 4f,
    isLiked: Boolean = false,
    isFlagged: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(YumBlack.copy(0.05f))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1680399524821-d4e6b225b0ee?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
                contentDescription = "",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Column(

            ) {
                Text(userName, style = semi, color = YumGreen)
                Spacer(modifier = Modifier.height(4.dp))
                Text(time, style = normal)
            }
        }
        Text(text, style = normal)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),

                ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Text(String.format("%.1f", star), style = normal)
            }

            Row {
                FilledIconButton(
                    onClick = {},
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(Icons.Outlined.ThumbUp, "", modifier = Modifier.size(16.dp))

                }
                Spacer(modifier = Modifier.width(16.dp))
                FilledIconButton(
                    onClick = {},
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(Icons.Outlined.ThumbUp, "", modifier = Modifier.size(16.dp))

                }
            }
        }
    }


}
