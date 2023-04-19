package com.anbui.yum.presentation.search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anbui.yum.R
import com.anbui.yum.common.component.YumRecipeCard
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.domain.model.Recipe
import com.anbui.yum.ui.theme.YumBlack


val suggestions = listOf(
    "egg",
    "flour",
    "corn",
    "milk",
    "water",
    "sugar",
    "salt",
    "rice",
    "cream",
    "ab",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
    "asd",
)

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
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-4).dp),
                query = uiState.searchText,
                onQueryChange = viewModel::onSearchTextChange,
                onSearch = { closeSearchBar() },
                active = active,
                onActiveChange = {
                    active = it
                    if (!active) focusManager.clearFocus()
                },
                placeholder = { Text("Hinted search textz") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (active && uiState.searchText.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                viewModel.onSearchTextChange("")
                            },
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }

                    } else {
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(Icons.Default.MoreVert, contentDescription = null)
                        }
                    }
                },
                shape = RectangleShape,
                colors = SearchBarDefaults.colors(
                    containerColor = Color.White,
                    dividerColor = Color.Transparent,
                ),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    itemsIndexed(
                        suggestions.filter {
                            it.lowercase().contains(uiState.searchText)
                        }.take(10),
                    ) { idx, suggestion ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    suggestion,
                                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                                    color = YumBlack.copy(0.7f),
                                )
                            },
                            modifier = Modifier.clickable {
//                                text = resultText
                                closeSearchBar()
                                viewModel.onSearchTextChange(suggestion)
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = if (idx % 2 != 0) Color.White
                                else YumBlack.copy(0.2f),
                            ),
                        )
                    }
                }
            }
            Column(modifier = Modifier.fillMaxSize()) {
                FilterSection()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(12) {
                           YumRecipeCard(recipe = Recipe())
                    }
                }
            }




        }
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
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("10 RESULT", color = Color.White, fontSize = 12.sp)

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("FILTER", color = Color.White, fontSize = 12.sp)
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(14.dp),
            )
        }
    }
}





