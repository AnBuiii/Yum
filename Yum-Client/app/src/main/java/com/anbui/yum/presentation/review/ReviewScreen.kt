package com.anbui.yum.presentation.review

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.anbui.yum.common.component.RatingBar
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.YumOrange
import org.koin.androidx.compose.getViewModel

@Composable
fun ReviewScreen(
    recipeId: String,
    viewModel: ReviewViewModel = getViewModel(),
) {

    val uiState by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                horizontal = 24.dp,
                vertical = 40.dp,
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                uiState.recipe.title,
                fontWeight = FontWeight.Bold,
            )
            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = YumBlack.copy(0.1f),
                    contentColor = YumBlack,
                ),
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "",
                )
            }
        }

        Text(
            text = "Leave a Review",
            modifier = Modifier.padding(
                top = 24.dp,
                bottom = 24.dp,
            ),
            color = YumBlack,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        )

        YumDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                model = uiState.userInfo.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
            )
            Column {
                Text(
                    uiState.userInfo.name,
                    color = YumGreen,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "Posting publicly",
                    fontSize = 13.sp,
                )
            }
        }

        YumDivider()

        Text("RATE THIS RECIPE")

        var a by remember {   mutableStateOf(1f)     }

        RatingBar(
            value = a,
            onValueChange = { a = it },
            onRatingChanged = {
                Log.d("Rating", a.toString())
            }
//            modifier = Modifier.height(30.dp)
        )


    }

    LaunchedEffect(true) {
        viewModel.init(recipeId)
    }
}
