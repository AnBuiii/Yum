package com.anbui.yum.presentation.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.common.component.YumSurface
import com.anbui.yum.domain.model.UserInfo
import com.anbui.yum.ui.theme.YumGreen
import org.koin.androidx.compose.getViewModel


private val TopBarHeight = 56.dp
private val TitleHeight = 300.dp


//private val MinTitleOffset = 56.dp
//private val GradientScroll = 180.dp
//private val ImageOverlap = 115.dp
//private val BottomBarHeight = 56.dp
//
//
//private val MinImageOffset = 12.dp
//private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
//private val ExpandedImageSize = 300.dp
//private val CollapsedImageSize = 150.dp
//private val HzPadding = Modifier.padding(horizontal = 24.dp)


@ExperimentalAnimationApi
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    onOpenScreen: (String) -> Unit = {},
    restartApp: () -> Unit = {},
    viewModel: UserViewModel = getViewModel(),
) {
    val uiState by viewModel.uiState
    var showWarningDialog by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()

    YumSurface {
        if (uiState.userInfo.userId.isBlank()) {
            AnonymousSection(
                onSignIn = { viewModel.onSignInTap(onOpenScreen) },
                onSignUp = { viewModel.onSignUpTap(onOpenScreen) },
            )

        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Title(
                    scroll.value,
                    uiState.userInfo,
                )
                Header()
                Body(scroll)
                HeaderItem(
                    scrollValue = scroll.value,
                    userInfo = uiState.userInfo,
                    logout = {
                        viewModel.logout()
                        restartApp()
                    },
                )

            }
        }
    }
}

@Composable
fun HeaderItem(
    scrollValue: Int,
    userInfo: UserInfo,
    logout: () -> Unit = {},
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "",
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "",
                )
            }
            IconButton(
                onClick = {
                    logout()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "",
                )
            }
        }
    }


}

@Composable
private fun AnonymousSection(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit = {},
    onSignUp: () -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ElevatedAssistChip(
                modifier = Modifier.size(
                    height = 48.dp,
                    width = 200.dp,
                ),
                onClick = onSignIn,
                label = { Text("Sign In") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "",
                        Modifier.size(AssistChipDefaults.IconSize),
                    )
                },
            )
            ElevatedAssistChip(
                modifier = Modifier.size(
                    height = 48.dp,
                    width = 200.dp,
                ),
                onClick = onSignUp,
                label = { Text("Sign Up") },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_person_add_24),
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize),
                    )
                },
            )
        }
    }
}

@Composable
private fun Title(
    scrollValue: Int,
    userInfo: UserInfo,
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
        Image(
            painter = painterResource(id = R.drawable.food_1),
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
            .background(MaterialTheme.colorScheme.background),
    )
}

@Composable
private fun Body(
//    related: List<SnackCollection>,
    scroll: ScrollState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(scroll)
            .fillMaxWidth(),

        ) {
        Spacer(Modifier.height(TitleHeight + TopBarHeight))

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


        listOf(
            "All Yum",
            "Break fast",
            "Desserts",
            "Dinners",
            "Drinks",
            "Sides",
        ).forEach {
            Box(
                modifier = Modifier
                    .aspectRatio(2.5f)
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_1),
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
                        it,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            "1",
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


