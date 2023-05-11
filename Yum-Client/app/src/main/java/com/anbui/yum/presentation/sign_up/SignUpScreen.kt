package com.anbui.yum.presentation.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.anbui.yum.common.ext.buttonModifier
import com.anbui.yum.common.ext.fieldModifier
import org.koin.androidx.compose.getViewModel
import com.anbui.yum.R.string as AppText


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = getViewModel(),
) {

    val uiState by viewModel.uiState


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        //email
        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            modifier = Modifier.fieldModifier(),
            placeholder = { Text("Email") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                )
            },
        )

        //password
        OutlinedTextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange,
            modifier = Modifier.fieldModifier(),
            placeholder = { Text("Password") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Email",
                )
            },

            )

        //repeat password
        OutlinedTextField(
            value = uiState.repeatPassword,
            onValueChange = viewModel::onRepeatPasswordChange,
            modifier = Modifier.fieldModifier(),
            placeholder = { Text("Repeat Password") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Email",
                )
            },

            )

        Button(
            onClick = { viewModel.onSignUp(openAndPopUp) },
            modifier = Modifier.buttonModifier(),
        ) {
            Text(text = stringResource(id = AppText.sign_up))
        }
    }
}

//@Composable
////fun EmailTextField

