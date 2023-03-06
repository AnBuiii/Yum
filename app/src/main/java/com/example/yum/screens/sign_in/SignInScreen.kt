package com.example.yum.screens.sign_in

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    openAndPopUp: (String, String) -> Unit = { _, _ -> },
    ) {

    val uiState by viewModel.uiState
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            TextField(value = uiState.email, onValueChange = viewModel::onEmailChange)
            TextField(value = uiState.password, onValueChange = viewModel::onPasswordChange)
            Button(
                onClick = {
                    viewModel.onSignInClick(openAndPopUp)
                },
            ) {
                Text("Sign in")
            }

        }
    }
}