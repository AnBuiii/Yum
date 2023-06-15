package com.anbui.yum.presentation.cart.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.anbui.yum.R
import com.anbui.yum.common.ext.timeAgo
import com.anbui.yum.domain.model.MealPlan
import com.anbui.yum.ui.theme.YumBlack
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlanItem(mealPlan: MealPlan, onTimeTap: () -> Unit = {}, onDoneTap: () -> Unit = {}) {

    Row(
        modifier = Modifier
            .height(108.dp)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AsyncImage(
            model = mealPlan.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .size(84.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
        Column {
            Text(
                mealPlan.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Text(
                mealPlan.time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli().timeAgo(),
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier.clickable { onTimeTap() },
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                IconButton(
                    onClick = { onDoneTap() },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black.copy(0.4f)),
                    modifier = Modifier.size(22.dp),
                ) {
                    Icon(
                        painter = if (!mealPlan.isDone) painterResource(id = R.drawable.unchecked) else painterResource(id = R.drawable.checked),
                        contentDescription = "",
                        tint = YumBlack,
                    )
                }
                Text(
                    text = "Made it",
                    fontSize = 14.sp,
                    color = Color.Black.copy(alpha = 0.4f),
                )
            }
        }
    }
}
