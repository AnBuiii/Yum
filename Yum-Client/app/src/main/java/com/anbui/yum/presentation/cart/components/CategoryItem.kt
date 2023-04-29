package com.anbui.yum.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.sp
import com.anbui.yum.R
import com.anbui.yum.common.component.FractionalThreshold
import com.anbui.yum.common.component.rememberSwipeableState
import com.anbui.yum.common.component.swipeable
import com.anbui.yum.domain.model.ShoppingList
import com.anbui.yum.ui.theme.YumBlack
import kotlin.math.roundToInt


@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    shoppingList: ShoppingList,
    onCheck: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {

    val boxSize = 150.dp
    val swipeState = rememberSwipeableState(initialValue = 0)
    val pxSize = with(LocalDensity.current) { boxSize.toPx() }
    val anchor = mapOf(
        0f to 0,
        -pxSize to 1,
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
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
                .width(150.dp)
                .fillMaxHeight(),
//                                horizontalArrangement = Arrangement.End
        ) {
            YumIconButton(
                onClick = onEdit,
                text = "EDIT",
                icon = Icons.Default.Edit,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            )
            YumIconButton(
                onClick = onDelete,
                text = "DELETE",
                icon = R.drawable.remove_circle,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            )

        }
        ListItem(
            headlineContent = {
                Text(
                    "1 pinch chili flakes",
                    color = Color.Black.copy(0.7f),
                    fontSize = 14.sp,
                )
            },
            leadingContent = {
                IconButton(onClick = onCheck) {
                    Icon(
                        painter = painterResource(id = R.drawable.unchecked),
                        contentDescription = "",
                        tint = YumBlack,
                    )
                }
            },
            supportingContent = {
                Text(
                    shoppingList.recipeName,
                    color = Color.Black.copy(0.7f),
                    fontSize = 14.sp,
                )
            },
            modifier = Modifier
                .offset {
                    IntOffset(
                        swipeState.offset.value.roundToInt(),
                        0,
                    )
                },
            trailingContent = {
                IconButton(onClick = onCheck) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "",
                        tint = YumBlack
                    )
                }
            },
            colors = ListItemDefaults.colors(containerColor = Color.White),
        )
        Divider(
            color = Color.Black.copy(0.1f),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter),
        )


    }
}
