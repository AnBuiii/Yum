package com.anbui.yum.screens.search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.yum.R
import com.anbui.yum.common.component.YumRecipeCard
import com.anbui.yum.common.component.YumSurface



@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    onRecipeClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState
    val searchHasFocused = remember { mutableStateOf(false) }
    var active by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    fun closeSearchBar() {
        focusManager.clearFocus()
        active = false
    }

    YumSurface(
        modifier = Modifier.fillMaxSize()
    ) {


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

//            SearchField(
//                focusRequester = focusRequester,
//                text = uiState.searchText,
//                onTextChange = viewModel::onSearchTextChange,
//                onSearchFocusChange = {
//                    searchHasFocused.value = it
//                }
//            )
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = uiState.searchText,
                onQueryChange = viewModel::onSearchTextChange,
                onSearch = { closeSearchBar() },
                active = active,
                onActiveChange = {
                    active = it
                    if (!active) focusManager.clearFocus()
                },
                placeholder = { Text("Hinted search text") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
                shape = RectangleShape,
                colors = SearchBarDefaults.colors(
                    containerColor = Color.Transparent
                )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(4) { idx ->
                        val resultText = "Suggestion $idx"
                        ListItem(
                            headlineContent = { Text(resultText) },
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            modifier = Modifier.clickable {
//                                text = resultText
                                closeSearchBar()
                            }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    FilterSection()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(12) {
                            YumRecipeCard()
                        }
                    }
                }
                SearchSuggestionSection(
                    isVisible = searchHasFocused.value && uiState.searchText.isNotBlank(),
                    modifier = Modifier.align(Alignment.TopCenter)
                )
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
    onSearchFocusChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { onSearchFocusChange(it.isFocused) },
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

@Composable
private fun FilterSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 4.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("10 RESULT", color = Color.White, fontSize = 12.sp)

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("FILTER", color = Color.White, fontSize = 12.sp)
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun SearchSuggestionSection(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(initialAlpha = 0.3f),
        exit = fadeOut()
    ) {
        Log.d("focus", "hello")
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .shadow(1.dp)
        ) {
            items(10) { b ->
                Box(
                    modifier = Modifier
                        .background(
                            if (b % 2 == 0) Color.White
                            else Color(117, 117, 117)
                        )
                        .fillMaxWidth()
                        .clickable { }

                ) {
                    Text(
                        text = "$b",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}




