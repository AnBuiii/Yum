package com.example.yum.screens.user

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.SIGNIN_SCREEN
import com.example.yum.SIGNUP_SCREEN
import com.example.yum.R.drawable as AppDrawable

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    onOpenScreen: (String) -> Unit = {},
    restartApp: (String) -> Unit = {},
    viewModel: UserViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = UserUiState(false))
    var showWarningDialog by remember { mutableStateOf(false) }



    if (uiState.isAnonymousAccount) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Card(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = { onOpenScreen(SIGNIN_SCREEN) },
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Sign In")
                    Icon(
                        painterResource(id = AppDrawable.baseline_person_add_24),
                        contentDescription = "",
                    )
                }
            }
            Card(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = { onOpenScreen(SIGNUP_SCREEN) },
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Sign up")
                    Icon(
                        painter = painterResource(id = AppDrawable.baseline_person_add_24),
                        contentDescription = "",
                    )
                }
            }
        }

    } else {
        Button(
            onClick = {
                showWarningDialog = true
            },
        ) {
        }
        if (showWarningDialog) {
            AlertDialog(
                onDismissRequest = { showWarningDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.onSignOutClick(restartApp)
                            showWarningDialog = false
                        },
                    ) {

                    }
                },
            )
        }

    }

}


@ExperimentalAnimationApi
@Preview
@Composable
fun UserScreenPreview() {
    UserScreen()
}