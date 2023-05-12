package com.anbui.yum.presentation.review

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.anbui.yum.common.component.RatingBar
import com.anbui.yum.common.component.RatingBarConfig
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.YumOrange
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReviewScreen(
    recipeId: String,
    viewModel: ReviewViewModel = getViewModel(),
    onBack: () -> Unit,
) {

    val uiState by viewModel.uiState
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                horizontal = 24.dp,
                vertical = 40.dp,
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    },
                )
            },
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

        Text(
            "RATE THIS RECIPE",
            fontWeight = FontWeight.Bold,
            color = YumBlack,
            fontSize = 14.sp,
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = 24.dp,
            ),
        )

        var a by remember { mutableStateOf(1f) }

        RatingBar(
            value = uiState.rating,
            onValueChange = viewModel::onRatingChange,
            onRatingChanged = { },
            config = RatingBarConfig().activeColor(YumOrange).inactiveColor(YumBlack.copy(0.4f)),
//            modifier = Modifier.height(30.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = uiState.comment,
            onValueChange = viewModel::onCommentChange,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = YumBlack.copy(0.1f),
                focusedContainerColor = YumBlack.copy(0.1f),
            ),
            maxLines = 3,
            minLines = 3,
            placeholder = { Text("asasdadadasdd") },
        )

        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = {
                coroutineScope.launch {
                    if (viewModel.onSubmit()) {
                        onBack()
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text("Submit")
        }

    }

    LaunchedEffect(true) {
        viewModel.init(recipeId)
    }
}

