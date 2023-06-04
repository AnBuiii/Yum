package com.anbui.yum.presentation.user.tabs

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.YumOrange

@Composable
fun AnonymousSection(
    modifier: Modifier = Modifier,
    onSignIn: (String, String) -> Unit = { _, _ -> },
    onSignUp: (String, String) -> Unit = { _, _ -> },
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showEmailWarnings by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 26.sp),
                color = YumOrange,
                modifier = Modifier.padding(vertical = 16.dp),
            )

            Text(
                "Connect to personalize your recipe discovery",
                color = Color.Black.copy(0.5f),
                fontSize = 13.sp,
            )

            Text(
                "Your recipe are waiting",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = YumGreen,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f),
                    focusedSupportingTextColor = Color.Red,
                    unfocusedSupportingTextColor = Color.Red,
                ),
                placeholder = {
                    Text(
                        "Email Address",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(0.25f),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                maxLines = 1,
                supportingText = { if (showEmailWarnings) Text(text = "Email not correct") else Text(text = "") },
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = YumGreen,
                    unfocusedIndicatorColor = Color.Black.copy(0.25f),
                ),
                placeholder = {
                    Text(
                        "Password",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(0.25f),
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                TextButton(
                    onClick = {
                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            onSignIn(
                                email,
                                password,
                            )
                            showEmailWarnings = false

                        } else {
                            showEmailWarnings = true
                        }

                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = YumGreen,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier.size(64.dp),
                ) {
                    Text(
                        text = "Login",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Text(
                    "or",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black.copy(0.4f),
                )

                TextButton(
                    onClick = {
                        onSignUp(
                            email,
                            password,
                        )
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = YumGreen,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier.size(64.dp),
                ) {
                    Text(
                        text = "Signup",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF,
)
@Composable
fun AnonymousReview() {
    AnonymousSection()
}
