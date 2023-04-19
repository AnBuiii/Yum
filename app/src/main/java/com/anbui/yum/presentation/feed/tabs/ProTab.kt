package com.anbui.yum.presentation.feed.tabs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.yum.domain.model.Collection
import com.anbui.yum.presentation.feed.component.ProItem


val items =
    listOf<Collection>(
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
        Collection(name = "", description = "description", recipes = listOf(), userId = "", id = ""),
    )

@Composable
fun ProTab(
    modifier: Modifier = Modifier,
) {


    LazyColumn {
        items(items) { item ->
            ProItem(collection = item)
        }
    }

}
