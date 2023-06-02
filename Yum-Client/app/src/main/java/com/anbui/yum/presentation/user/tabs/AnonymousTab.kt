package com.anbui.yum.presentation.user.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anbui.yum.R

@Composable
fun AnonymousSection(
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
