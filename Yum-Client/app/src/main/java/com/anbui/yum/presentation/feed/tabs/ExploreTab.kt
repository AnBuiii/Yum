package com.anbui.yum.presentation.feed.tabs

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.yum.presentation.feed.component.CollectionItem

@Composable
fun ExploreTab(
    modifier: Modifier = Modifier,
    onCollectionTab: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(10) { item ->
            CollectionItem(
                onTap =  onCollectionTab
            )
        }
    }

}
