package com.anbui.yum.presentation.cart.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.anbui.yum.R
import com.anbui.yum.common.component.FractionalThreshold
import com.anbui.yum.common.component.rememberSwipeableState
import com.anbui.yum.common.component.swipeable
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.ui.theme.YumGreen
import kotlin.math.roundToInt


@Composable
fun CategoryItem(
    isVisible: Boolean = true,
    shoppingList: ShoppingList,
    onCheck: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    AnimatedVisibility(visible = isVisible) {
        val boxSize = 150.dp
        val swipeState = rememberSwipeableState(initialValue = 0)
        val pxSize = with(LocalDensity.current) { boxSize.toPx() }
        val anchor = mapOf(0f to 0, -pxSize to 1)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray.copy(alpha = 0.2f))
                .swipeable(
                    state = swipeState,
                    anchors = anchor,
                    thresholds = { _, _ ->
                        FractionalThreshold(0.5f)
                    },
                    orientation = Orientation.Horizontal,
                ),
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(150.dp),
//                                horizontalArrangement = Arrangement.End
            ) {
                YumIconButton(
                    onClick = onEdit,
                    text = "EDIT",
                    icon = Icons.Default.Edit,
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                )
                YumIconButton(
                    onClick = onDelete,
                    text = "DELETE",
                    icon = R.drawable.remove_circle,
                    modifier = Modifier.weight(1f).fillMaxHeight()
                )

            }
            ListItem(
                headlineContent = { Text(shoppingList.foodName) },
                leadingContent = {
                    IconButton(onClick = onCheck) {
                        Icon(
                            painter = painterResource(id = R.drawable.unchecked),
                            contentDescription = "",
                            tint = YumGreen,
                        )
                    }
                },
                supportingContent = { Text(shoppingList.recipeName) },
                modifier = Modifier
                    .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) },
                colors = ListItemDefaults.colors(containerColor = Color.White),
            )
        }

    }
}
