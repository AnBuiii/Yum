package com.example.yum.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yum.SIGNIN_SCREEN
import com.example.yum.SIGNUP_SCREEN
import com.example.yum.R.drawable as AppDrawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    onOpenScreen: (String) -> Unit = {},
    viewModel: UserViewModel = hiltViewModel(),
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Card(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {onOpenScreen(SIGNIN_SCREEN)}
                ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Sign In")
                    Icon(painterResource(id = AppDrawable.baseline_person_add_24), contentDescription = "")
                }
            }
            Card(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {onOpenScreen(SIGNUP_SCREEN)}
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Create account")
                    Icon(painter = painterResource(id = AppDrawable.baseline_person_add_24), contentDescription = "")
                }
            }
        }
    }
}

@Preview
@Composable
fun UserScreenPreview() {
    UserScreen()
}