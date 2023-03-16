package com.example.yum.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.R
import com.example.yum.common.component.YumSurface


@ExperimentalMaterial3Api
@Composable
fun SearchRoute(
    onRecipeClick: (String) -> Unit,
) {
    SearchScreen(onRecipeClick = {}, modifier = Modifier)
}

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    val focusRequester = remember { FocusRequester() }

    YumSurface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchField(
                focusRequester = focusRequester,
                text = uiState.searchText,
                onTextChange = viewModel::onSearchTextChange
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "8 RESULT")
                    Button(onClick = { /*TODO*/ }) {

                    }
                }
                Column {
//                    Text()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun SearchField(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
    text: String = "",
    onTextChange: (String) -> Unit = {},
    focusManager: FocusManager = LocalFocusManager.current,
) {
    Row(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .focusRequester(focusRequester),
            value = text,
            onValueChange = onTextChange,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                    )
                }
            },
            trailingIcon = {
                if (text.isBlank())
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_photo_camera),
                            contentDescription = ""
                        )
                    }
                else
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = ""
                        )
                    }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                }
            ),
            placeholder = { Text("Search for recipe") }
        )
    }
}





