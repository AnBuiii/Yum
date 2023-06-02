package com.anbui.yum.presentation.recipe.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.domain.model.Collection
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import kotlinx.coroutines.launch

@Composable
fun AddToCollection(
    visible: Boolean = false,
    onSubmit: () -> Unit = {},
    collections: List<Collection>,
    recipe: Recipe,
    onRemove: (String) -> Unit,
    onInsert: suspend (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
//                    horizontal = 24.dp,
                    vertical = 24.dp,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 16.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    "Collection",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                IconButton(
                    onClick = onSubmit,
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
            YumDivider()

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 22.dp,
                    )
                    .clickable { },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_add_circle),
                    contentDescription = "",
                    tint = YumGreen,
                )
                Text(
                    "New Collection",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                )
            }

            YumDivider()

            collections.forEach {
                var isChecked by remember { mutableStateOf(it.recipes.any { rcp -> rcp.id == recipe.id }) }
                ListItem(
                    headlineContent = {
                        Text(
                            it.name,
                            color = Color.Black.copy(0.7f),
                            fontSize = 14.sp,
                        )
                    },
                    leadingContent = {
                        IconButton(
                            onClick = {
                                if (isChecked) {
                                    onRemove(it.id)
                                } else {
                                    coroutineScope.launch {
                                        onInsert(it.id)
                                    }
                                }
                                isChecked = !isChecked
                            },
                        ) {
                            Icon(
                                painter = if (!isChecked) painterResource(id = R.drawable.unchecked) else painterResource(id = R.drawable.checked),
                                contentDescription = "",
                                tint = YumBlack,
                            )
                        }
                    },
                    colors = ListItemDefaults.colors(containerColor = Color.White),
                )
            }


            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {
                    onSubmit()
                },
                colors = ButtonDefaults.buttonColors(containerColor = YumGreen),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text("Submit")
            }

        }

        LaunchedEffect(true) {
//            viewModel.init(recipeId)
        }
    }
}
