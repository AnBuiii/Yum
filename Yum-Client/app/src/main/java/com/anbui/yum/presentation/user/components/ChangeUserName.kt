package com.anbui.yum.presentation.user.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.anbui.yum.ui.theme.YumGreen

@Composable
fun ChangeUserName(
    modifier: Modifier = Modifier,
    visible: Boolean,
    oldName: String,
    onSubmit: (String) -> Unit = {},
    onClose: () -> Unit = {},
) {
    var collectionName by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier.clickable { },
        ) {
            IconButton(
                onClick = { onClose() },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White.copy(0.75f),
                ),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "",
                )
            }
            TextField(
                modifier = Modifier
                    .align(Alignment.Center)
                    .focusRequester(focusRequester)
                    .fillMaxWidth(),
                value = collectionName,
                onValueChange = { collectionName = it },
                keyboardActions = KeyboardActions(onDone = { onSubmit(collectionName) }),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                placeholder = {
                    Text(
                        oldName,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black,
                    focusedIndicatorColor = YumGreen,
                    focusedTextColor = Color.White.copy(0.75f),
                ),
            )
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

}
