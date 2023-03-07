package com.example.yum.screens.feed


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.yum.R
import com.example.yum.R.string as AppText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onRecipeTap: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var a: String = ""
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        //app text
        Text(
            text = stringResource(id = AppText.app_name),
            modifier = Modifier.padding(start = 16.dp, top = 32.dp),
            style = MaterialTheme.typography.displayMedium
        )

        // Quote
        Text(
            text = stringArrayResource(id = R.array.quote)[0],
            modifier = Modifier.padding(start = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(16.dp)
                .height(IntrinsicSize.Max),

            ) {
            OutlinedTextField(
                value = a,
                onValueChange = {},
                leadingIcon = { Icon(imageVector = Icons.Default.Search, "") },
                placeholder = { Text(text = stringResource(id = AppText.search_placeholder)) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f)
            )
            OutlinedIconButton(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Filter",
                    modifier = Modifier.size(24.dp)
                )
            }
            OutlinedIconButton(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Filter"
                )
            }
        }
    }
}