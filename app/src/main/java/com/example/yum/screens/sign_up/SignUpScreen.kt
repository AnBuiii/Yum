package com.example.yum.screens.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val uitState by viewModel.uiState

    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        //email
        OutlinedTextField(
            value = uitState.email,
            onValueChange = viewModel::onEmailChange,
            modifier = fieldModifier,
            placeholder = { Text("Email") },
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
        )

        //password
        OutlinedTextField(
            value = uitState.password,
            onValueChange = viewModel::onPasswordChange,
            modifier = fieldModifier,
            placeholder = { Text("Password") },
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Email") }

        )

        //repeat password
        OutlinedTextField(
            value = uitState.repeatPassword,
            onValueChange = viewModel::onRepeatPasswordChange,
            modifier = fieldModifier,
            placeholder = { Text("Repeat Password") },
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Email") }

        )

        Button(onClick = { viewModel.onSignUp { } }) {

        }
    }
}

//@Composable
////fun EmailTextField

