package com.anbui.yum.presentation.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.common.snackbar.SnackbarManager
import com.anbui.yum.common.snackbar.SnackbarMessage
import com.anbui.yum.domain.model.Collection
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.presentation.user.components.ChangeUserDescription
import com.anbui.yum.presentation.user.components.ChangeUserName
import com.anbui.yum.presentation.user.components.NewCollection
import com.anbui.yum.presentation.user.tabs.AnonymousSection
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


private val TopBarHeight = 56.dp
private val TitleHeight = 300.dp

@ExperimentalAnimationApi
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    onOpenScreen: (String) -> Unit = {},
    restartApp: () -> Unit = {},
    onCollectionTab: (String) -> Unit = {},
    viewModel: UserViewModel = getViewModel(),
) {
    val uiState by viewModel.uiState
    val scroll = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    YumSurface {
        if (uiState.userInfo.userId.isBlank()) {
            AnonymousSection(
                onSignIn = { email, password ->
                    coroutineScope.launch {
                        if (viewModel.login(
                                email,
                                password,
                            ).isNotEmpty()
                        ) {
                            restartApp()
                            SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Logged in"))
                        }
                    }
                },
//                onSignUp = { viewModel.onSignUpTap(onOpenScreen) },
            )
        } else {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(
                            drawerShape = RectangleShape,
                            drawerContainerColor = Color.White,
                        ) {

                            Spacer(Modifier.height(12.dp))
                            NavigationDrawerItem(
                                label = { Text("Edit user") },
                                selected = false,
                                onClick = { viewModel.changeChangeUsernameVisible(true) },
                                icon = {
                                    Icon(
                                        Icons.Default.AccountBox,
                                        contentDescription = null,
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            )
                            Spacer(Modifier.height(12.dp))


                            NavigationDrawerItem(
                                label = { Text("Log out") },
                                selected = false,
                                onClick = {
                                    viewModel.logout()
                                    restartApp()
                                },
                                icon = {
                                    Icon(
                                        Icons.Default.ExitToApp,
                                        contentDescription = null,
                                    )
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            )
                        }
                    },
                    content = {
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                            Scaffold(
                                floatingActionButton = {
                                    IconButton(
                                        onClick = {
                                            viewModel.changeNewCollectionVisible(true)
                                        },
                                        modifier = Modifier.size(64.dp),
                                        colors = IconButtonDefaults.iconButtonColors(
                                            containerColor = Color.White,
                                            contentColor = YumBlack,
                                        ),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = null,
                                        )
                                    }
                                },
                                containerColor = Color.White,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.White)
                                        .padding(it),
                                ) {
                                    Title(
                                        scrollValue = scroll.value,
                                        userInfo = uiState.userInfo,
                                        onUsernameClick = { viewModel.changeChangeUsernameVisible(true) },
                                        onUserDescriptionClick = { viewModel.changeChangeUsernameVisible(true) },
                                    )
                                    Header()
                                    Body(
                                        uiState.collections,
                                        scroll,
                                        onCollectionClick = onCollectionTab,
                                    )
                                    HeaderItem(
                                        scrollValue = scroll.value,
                                        userInfo = uiState.userInfo,
                                        openDrawer = {
                                            coroutineScope.launch {
                                                drawerState.open()
                                            }
                                        },
                                    )

                                }
                            }

                        }
                    },
                )
            }

        }
        NewCollection(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            visible = uiState.isNewCollectionVisible,
            onSubmit = {
                viewModel.onNewCollection(it)
                viewModel.changeNewCollectionVisible(false)
            },
            onClose = {
                viewModel.changeNewCollectionVisible(false)
            },
        )
        ChangeUserName(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            visible = uiState.isChangeUsernameVisible,
            onSubmit = {
                viewModel.changeUsername(it)
                viewModel.changeChangeUsernameVisible(false)
                viewModel.changeChangeUserDescriptionVisible(true)
            },
            oldName = uiState.userInfo.name,
            onClose = {
                viewModel.changeChangeUsernameVisible(false)
            },
        )

        ChangeUserDescription(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            visible = uiState.isChangeUserDescriptionVisible,
            onSubmit = {
                viewModel.changeUserDescription(it)
                viewModel.changeUserInfo()
                viewModel.changeChangeUserDescriptionVisible(false)
            },
            oldDescription = uiState.userInfo.title,
            onClose = {
                viewModel.changeChangeUserDescriptionVisible(false)
            },
        )
    }
    LaunchedEffect(Unit) {
        viewModel.init()
    }
}

@Composable
fun HeaderItem(
    scrollValue: Int,
    userInfo: UserInfo,
    openDrawer: () -> Unit = {},
) {
    val bodyScrollValueToShowText = with(LocalDensity.current) { TitleHeight.toPx() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        AnimatedVisibility(
            visible = scrollValue >= bodyScrollValueToShowText.toInt(),
            enter = fadeIn(initialAlpha = 0.2f),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.TopStart),
        ) {
            Text(
                userInfo.name,
                modifier = Modifier.padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            IconButton(onClick = { openDrawer() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                )
            }
        }
    }


}


@Composable
private fun Title(
    scrollValue: Int,
    userInfo: UserInfo,
    onUsernameClick: () -> Unit = {},
    onUserDescriptionClick: () -> Unit = {},
) {
    val maxOffset = with(LocalDensity.current) { TopBarHeight.toPx() }
    val minOffset = with(LocalDensity.current) { (TopBarHeight - TitleHeight).toPx() }
    Column(
        modifier = Modifier
            .heightIn(TitleHeight)
            .fillMaxWidth()
            .offset {
                val offset = (maxOffset - scrollValue / 2).coerceAtLeast(minOffset)
                IntOffset(
                    x = 0,
                    y = offset.toInt(),
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        AsyncImage(
            model = userInfo.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = userInfo.name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = userInfo.title,
            textAlign = TextAlign.Center,
            modifier = Modifier,
        )
    }
}

@Composable
private fun Header() {
    val headerHeight = 400.dp
    Spacer(
        modifier = Modifier
            .height(TopBarHeight)
            .fillMaxWidth()
            .background(Color.White),
    )
}

@Composable
private fun Body(
    collections: List<Collection>,
    scroll: ScrollState,
    onCollectionClick: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .verticalScroll(scroll)
            .fillMaxWidth()
            .padding(top = TitleHeight + TopBarHeight),

        ) {
//        Spacer(Modifier.height(TitleHeight + TopBarHeight))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = 8.dp)
                .clickable { },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "View by",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Your collection",
                color = YumGreen,
                fontSize = 12.sp,
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "",
                tint = YumGreen,
            )
        }


        collections.forEach {
            Box(
                modifier = Modifier
                    .aspectRatio(2.4f)
                    .fillMaxWidth()
                    .clickable {
                        onCollectionClick(it.id)
                    },
            ) {
                AsyncImage(
                    model = it.recipes.getOrNull(0)?.imageUrl,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.4f),
                                    Color.Black.copy(alpha = 0.6f),
                                ),
                            ),
                        )
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        it.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            "${it.recipes.size}",
                            color = Color.White,
                            fontSize = 18.sp,
                        )
                        Text(
                            "RECIPES",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10000.dp))
}


