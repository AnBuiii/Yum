package com.anbui.yum.presentation.recipe.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anbui.yum.ui.theme.YumBlack
import com.anbui.yum.ui.theme.YumGreen
import com.anbui.yum.ui.theme.YumOrange
import com.anbui.yum.ui.theme.normal
import kotlin.math.roundToInt

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    values: List<Int> = listOf(),
    colors: List<Color> = listOf(YumOrange, YumGreen, YumBlack),
    legend: List<String> = listOf("Protein", "Carbs", "Fat"),
    size: Dp = 100.dp,
) {

    val sumOfValues = values.sum()

    val proportions = values.map {
        it.toFloat() * 100 / sumOfValues
    }

    val sweepAngles = proportions.map {
        360 * it / 100
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(YumBlack.copy(0.1f))
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(
            modifier = Modifier
                .size(size = size),
        ) {

            var startAngle = -90f

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[i],
                    useCenter = true,
                )
                startAngle += sweepAngles[i]
            }
        }

        Spacer(modifier = Modifier.width(32.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for (i in values.indices) {
                DisplayLegend1(color = colors[i], legend = legend[i], value = proportions[i])
                if (i < values.lastIndex) Divider()
            }
        }
    }

}

@Composable
fun DisplayLegend1(color: Color, legend: String, value: Float) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color))

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = legend,
                style = normal,
            )
        }
        Text("${value.roundToInt()}%", style = normal)

    }
}
