package com.anbui.yum.presentation.cart

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.yum.common.component.YumDivider
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.presentation.cart.components.CartBottomSheet
import com.anbui.yum.presentation.cart.components.CartTopBar
import com.anbui.yum.presentation.cart.components.ShoppingItemCartBottomSheet
import com.anbui.yum.presentation.cart.tabs.PantryTab
import com.anbui.yum.presentation.cart.tabs.PlanTab
import com.anbui.yum.presentation.cart.tabs.ShopTab
import com.anbui.yum.ui.theme.YumBlack
import org.koin.androidx.compose.getViewModel


val cartTabList = listOf(
    "Plan",
    "Shop",
    "Pantry",
)

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun CartScreen(
    onRecipeTap: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = getViewModel(),
) {
    val uiState by viewModel.uiState
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val searchText by viewModel.searchText.collectAsState()
    val persons by viewModel.persons.collectAsState()

    var active by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current


    fun closeSearchBar() {
        focusManager.clearFocus()
        active = false
    }

    YumSurface(
        modifier.fillMaxSize(),
        color = Color.White,
    ) {
        Box {
            CartBottomSheet(
                isOpen = uiState.isBottomSheetOpen,
                onChangeOpenBottomSheet = viewModel::onChangeBottomSheet,
            )
            ShoppingItemCartBottomSheet(
                isOpen = uiState.isShoppingItemBottomSheetOpen,
                onChangeOpenBottomSheet = viewModel::onChangeShoppingBottomSheet,
                shoppingItem = uiState.onChangeShoppingItem,
                onSave = { amount, unit ->
                    viewModel.changeQuantity(
                        uiState.onChangeShoppingItem.id,
                        amount,
                        unit,
                    )
                },
            )

            // main screen
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                CartTopBar(
                    onMoreTap = { viewModel.onChangeBottomSheet(true) },
                    pagerState = pagerState,
                )
                YumDivider(
                    thickness = 1.dp,
                    color = Color.Black.copy(0.1f),
                )

                HorizontalPager(
                    pageCount = 3,
                    state = pagerState,
                ) { index ->
                    when (index) {
                        0 -> PlanTab()
                        1 -> ShopTab(
                            hmItems = uiState.hmItems,
//                        hmItems = a,
                            onCheck = viewModel::check,
                            onEdit = viewModel::openBottomSheet,
                            onRemove = viewModel::remove,
                            onRefresh = viewModel::getShoppingList,
                            onAddShoppingList = {
                                viewModel.onChangeSearch(true)
//                                active = true
                            },
                        )

                        2 -> PantryTab()
                    }
                }
            }

            LaunchedEffect(uiState.isSearchOpen) {
                Log.d(
                    "?",
                    uiState.isSearchOpen.toString(),
                )
            }
            AnimatedVisibility(
                visible = uiState.isSearchOpen,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = YumBlack.copy(0.4f))
                        .clickable {
                            closeSearchBar()
                            viewModel.onChangeSearch(false)
                        },
                ) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-8).dp),
                        query = searchText,
                        onQueryChange = viewModel::onSearchTextChange,
                        onSearch = {
                            closeSearchBar()
                        },
                        active = active,
                        onActiveChange = {
                            active = it
                            if (!active) focusManager.clearFocus()
                        },
                        placeholder = { Text("Search for ingredient") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                            )
                        },
                        trailingIcon = {
                            if (active && searchText.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        viewModel.onSearchTextChange("")
                                    },
                                ) {
                                    Icon(
                                        Icons.Default.Clear,
                                        contentDescription = null,
                                    )
                                }

                            } else {
                                IconButton(
                                    onClick = {},
                                ) {
                                    Icon(
                                        Icons.Default.MoreVert,
                                        contentDescription = null,
                                    )
                                }
                            }
                        },
                        shape = RectangleShape,
                        colors = SearchBarDefaults.colors(
                            containerColor = Color.White,
                            dividerColor = Color.Transparent,
                            inputFieldColors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Black,
                                cursorColor = Color.Blue,
                            ),
                        ),
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            items(
                                persons,
                            ) {suggestion ->
                                ListItem(
                                    headlineContent = {
                                        Text(
                                            suggestion.name,
                                            style = TextStyle(fontWeight = FontWeight.SemiBold),
                                            color = YumBlack.copy(0.7f),
                                        )
                                    },
                                    modifier = Modifier.clickable {
                                        closeSearchBar()
                                        viewModel.onChangeSearch(false)
                                        viewModel.onAddIngredient(suggestion.id)
                                    },
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.White,
                                    ),
                                )
                            }
                        }
                    }
                }

            }
        }


        // search appear

    }
    LaunchedEffect(true) {
        viewModel.getShoppingList()
    }
}






