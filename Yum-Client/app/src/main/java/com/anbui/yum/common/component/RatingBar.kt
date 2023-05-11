package com.anbui.yum.common.component

import android.util.Log
import android.view.MotionEvent
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.anbui.yum.common.component.RatingBarUtils.stepSized
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

sealed interface StepSize {
    object ONE : StepSize
    object HALF : StepSize
}

sealed interface RatingBarStyle {
    object Normal : RatingBarStyle
    object HighLighted : RatingBarStyle
}

//For ui testing
val StarRatingKey = SemanticsPropertyKey<Float>("StarRating")
var SemanticsPropertyReceiver.starRating by StarRatingKey


/**
 * Draws a Rating Bar on the screen according to the [RatingBarConfig] instance passed to the composable
 *
 * @param value is current selected rating count
 * @param config the different configurations applied to the Rating Bar.
 * @param onRatingChanged A function to be called when the click or drag is released and rating value is passed
 * @see [RatingBarConfig]
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    config: RatingBarConfig = RatingBarConfig(),
    onValueChange: (Float) -> Unit,
    onRatingChanged: (Float) -> Unit,
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }
    var lastDraggedValue by remember { mutableStateOf(0f) }
    val direction = LocalLayoutDirection.current

    Row(
        modifier = modifier
            .onSizeChanged { rowSize = it.toSize() }
            .pointerInput(
                Unit,
            ) {
                //handling dragging events
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (config.isIndicator || config.hideInactiveStars)
                            return@detectHorizontalDragGestures
                        onRatingChanged(lastDraggedValue)
                    },
                    onDragCancel = {

                    },
                    onDragStart = {

                    },
                    onHorizontalDrag = { change, _ ->
                        if (config.isIndicator || config.hideInactiveStars)
                            return@detectHorizontalDragGestures
                        change.consume()
                        val x1 = change.position.x.coerceIn(
                            0f,
                            rowSize.width,
                        )
                        val calculatedStars =
                            RatingBarUtils.calculateStars(
                                x1,
                                rowSize.width,
                                config.numStars,
                                config.padding.value.toInt(),
                            )
                        var newValue =
                            calculatedStars
                                .stepSized(config.stepSize)
                                .coerceIn(
                                    0f,
                                    config.numStars.toFloat(),
                                )

                        if (direction == LayoutDirection.Rtl)
                            newValue = config.numStars - newValue
                        onValueChange(newValue)
                        lastDraggedValue = newValue
                    },
                )
            }
            .pointerInteropFilter {
                if (config.isIndicator || config.hideInactiveStars)
                    return@pointerInteropFilter false
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        //handling when click events
                        val calculatedStars =
                            RatingBarUtils.calculateStars(
                                it.x,
                                rowSize.width,
                                config.numStars,
                                config.padding.value.toInt(),
                            )
                        var newValue =
                            calculatedStars
                                .stepSized(config.stepSize)
                                .coerceIn(
                                    0f,
                                    config.numStars.toFloat(),
                                )
                        if (direction == LayoutDirection.Rtl)
                            newValue = config.numStars - newValue
                        onValueChange(newValue)
                        onRatingChanged(newValue)
                    }
                }
                true
            },
    ) {
        ComposeStars(
            value,
            config,
        )
    }
}

@Composable
fun ComposeStars(
    value: Float,
    config: RatingBarConfig,
) {

    val ratingPerStar = 1f
    var remainingRating = value

    Row(
        modifier = Modifier
            .semantics { starRating = value },
    ) {
        for (i in 1..config.numStars) {
            val starRating = when {
                remainingRating == 0f -> {
                    0f
                }

                remainingRating >= ratingPerStar -> {
                    remainingRating -= ratingPerStar
                    1f
                }

                else -> {
                    val fraction = remainingRating / ratingPerStar
                    remainingRating = 0f
                    fraction
                }
            }
            if (config.hideInactiveStars && starRating == 0.0f)
                break
            RatingStar(
                fraction = starRating,
                config = config,
                modifier = Modifier
                    .padding(
                        start = if (i > 1) config.padding else 0.dp,
                        end = if (i < config.numStars) config.padding else 0.dp,
                    )
                    .size(size = config.size)
                    .testTag("RatingStar"),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    var rating by remember { mutableStateOf(3.3f) }
    RatingBar(
        value = rating,
        config = RatingBarConfig(),
        onValueChange = {
            rating = it
        },
    ) {
        Log.d(
            "TAG",
            "RatingBarPreview: $it",
        )
    }
}

object RatingBarUtils {

    /**
     *  @return selected or dragged stars
     *  float value that should be selected
     * */
    fun calculateStars(
        draggedWidth: Float, width: Float,
        numStars: Int, padding: Int,
    ): Float {
        var overAllComposeWidth = width
        val spacerWidth = numStars * (2 * padding)

        //removing padding's width
        overAllComposeWidth -= spacerWidth
        return if (draggedWidth != 0f)
            ((draggedWidth / overAllComposeWidth) * numStars)
        else 0f
    }


    /**
     *  @return rating stars float value depends on [StepSize]
     *
     *  case 1: if selected star value is 3.234345 and [StepSize] is StepSize.ONE,
     *  this function will return 3.0.
     *  more ex: [this] 3.634345, return value would be 3.
     *  [this] 4.96367, return value would be 4.
     *
     *  case 2: if selected star value is 3.234345 and [StepSize] is StepSize.HALF,
     *  this function will return 3.5. So, 3.5 stars to be selected.
     *  more ex: [this] 3.634345, return value would be 4.
     *
     * */
    fun Float.stepSized(stepSize: StepSize): Float {
        return if (stepSize is StepSize.ONE)
            this.roundToInt().toFloat()
        else {
            var value = this.toInt().toFloat()
            if (this < value.plus(0.5)) {
                if (this == 0f)
                    return 0f
                value = value.plus(0.5).toFloat()
                value
            } else
                this.roundToInt().toFloat()
        }
    }
}

class RatingBarConfig {

    /**
     * size for each star.
     */
    var size: Dp = 26.dp
        private set

    /**
     * stroke width for each star
     */
    var strokeWidth: Float = 1f
        private set

    /**
     * padding between each star.
     */
    var padding: Dp = 2.dp
        private set

    /**
     * Can be [RatingBarStyle.Normal] or [RatingBarStyle.HighLighted]
     */
    var style: RatingBarStyle = RatingBarStyle.Normal
        private set

    /**
     * numStars Sets the number of stars to show.
     */
    var numStars: Int = 5
        private set

    /**
     * isIndicator Whether this rating bar is only an indicator.
     */
    var isIndicator: Boolean = false
        private set

    /**
     * A [Color] representing an active star (or part of it).
     */
    var activeColor: Color = Color(0xffffd740)
        private set

    /**
     * A [Color] representing a inactive star (or part of it).
     */
    var inactiveColor: Color = Color(0xffffecb3)
        private set

    /**
     * A border [Color] shown on inactive star.
     */
    var inactiveBorderColor: Color = Color(0xFF888888)
        private set

    /**
     * Minimum value to trigger a change
     */
    var stepSize: StepSize = StepSize.ONE
        private set

    /**
     * Whether the inactive stars should be hidden.
     */
    var hideInactiveStars: Boolean = false
        private set

    /**
     * Sets the size of the star.
     * @param value the value in Dp
     */
    fun size(value: Dp): RatingBarConfig =
        apply {
            size = value
        }

    /**
     * Sets the stroke width of stars
     * @param value the value in Float
     */
    fun strokeWidth(value: Float): RatingBarConfig =
        apply {
            strokeWidth = value
        }

    /**
     * Sets the padding between star.
     * @param value the value in Dp.
     */
    fun padding(value: Dp): RatingBarConfig =
        apply {
            padding = value
        }

    /**
     * Sets the style of the composable.
     * @param value the value in Float.
     * @see [RatingBarStyle]
     */
    fun style(value: RatingBarStyle): RatingBarConfig =
        apply {
            style = value
        }

    /**
     * Sets whether this rating bar is only an indicator.
     * @param value the value in Boolean.
     */
    fun isIndicator(value: Boolean): RatingBarConfig =
        apply {
            isIndicator = value
        }

    /**
     * Sets the number of stars to show.
     * @param value the value in Int.
     */
    fun numStars(value: Int): RatingBarConfig =
        apply {
            numStars = value
        }

    /**
     * Sets the [Color] representing an active star (or part of it).
     * @param value the value in [Color]
     */
    fun activeColor(value: Color): RatingBarConfig =
        apply {
            activeColor = value
        }

    /**
     * Sets the [Color] representing a inactive star (or part of it).
     * @param value the value in [Color]
     */
    fun inactiveColor(value: Color): RatingBarConfig =
        apply {
            inactiveColor = value
        }

    /**
     * Sets the [Color] representing a inactive star (or part of it).
     * @param value the value in [Color]
     */
    fun inactiveBorderColor(value: Color): RatingBarConfig =
        apply {
            inactiveBorderColor = value
        }

    /**
     * Sets the minimum value to trigger a change.
     * @param value the value in [StepSize]
     */
    fun stepSize(value: StepSize): RatingBarConfig =
        apply {
            stepSize = value
        }

    /**
     * Whether the inactive stars should be hidden.
     * @param value the value in Boolean
     */
    fun hideInactiveStars(value: Boolean): RatingBarConfig =
        apply {
            hideInactiveStars = value
        }
}

@Composable
fun RatingStar(
    @FloatRange(
        from = 0.0,
        to = 1.0,
    ) fraction: Float,
    config: RatingBarConfig,
    modifier: Modifier = Modifier,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    Box(modifier = modifier) {
        FilledStar(
            fraction,
            config.activeColor,
            isRtl,
            config.strokeWidth,
        )
        EmptyStar(
            fraction,
            config,
            isRtl,
            config.strokeWidth,
        )
    }
}

@Composable
private fun FilledStar(fraction: Float, activeColor: Color, isRtl: Boolean, strokeWidth: Float) =
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                if (isRtl)
                    rtlFilledStarFractionalShape(fraction = fraction)
                else
                    FractionalRectangleShape(
                        0f,
                        fraction,
                    ),
            ),
    ) {
        val path = Path().addStar(size)

        drawPath(
            path,
            color = activeColor,
            style = Fill,
        ) // Filled Star
        drawPath(
            path,
            color = activeColor,
            style = Stroke(width = strokeWidth),
        ) // Border
    }
@Composable
private fun EmptyStar(
    fraction: Float,
    config: RatingBarConfig,
    isRtl: Boolean,
    strokeWidth: Float,
) =
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                if (isRtl)
                    rtlEmptyStarFractionalShape(fraction = fraction)
                else
                    FractionalRectangleShape(
                        fraction,
                        1f,
                    ),
            ),
    ) {
        val path = Path().addStar(size)
        if (config.style is RatingBarStyle.Normal)
            drawPath(
                path,
                color = config.inactiveColor,
                style = Fill,
            ) // Border
        else
            drawPath(
                path,
                color = config.inactiveBorderColor,
                style = Stroke(width = strokeWidth),
            ) // Border
    }

@Preview(showBackground = true)
@Composable
fun EmptyRatingStarPreview() {
    RatingStar(
        fraction = 0f,
        config = RatingBarConfig()
            .activeColor(Color(0xffffd740))
            .inactiveColor(Color.Gray)
            .style(RatingBarStyle.Normal),
        modifier = Modifier.size(20.dp),
    )
}

fun rtlEmptyStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 1f || fraction == 0f)
        FractionalRectangleShape(
            fraction,
            1f,
        )
    else FractionalRectangleShape(
        0f,
        1f - fraction,
    )
}

fun rtlFilledStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 0f || fraction == 1f)
        FractionalRectangleShape(
            0f,
            fraction,
        )
    else FractionalRectangleShape(
        1f - fraction,
        1f,
    )
}

@Stable
class FractionalRectangleShape(
    @FloatRange(
        from = 0.0,
        to = 1.0,
    ) private val startFraction: Float,
    @FloatRange(
        from = 0.0,
        to = 1.0,
    ) private val endFraction: Float,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Rectangle(
            Rect(
                left = (startFraction * size.width).coerceAtMost(size.width - 1f),
                top = 0f,
                right = (endFraction * size.width).coerceAtLeast(1f),
                bottom = size.height,
            ),
        )
    }

}

fun Path.addStar(
    size: Size,
    spikes: Int = 5,
    @FloatRange(
        from = 0.0,
        to = 0.5,
    ) outerRadiusFraction: Float = 0.5f,
    @FloatRange(
        from = 0.0,
        to = 0.5,
    ) innerRadiusFraction: Float = 0.2f,
): Path {
    val outerRadius = size.minDimension * outerRadiusFraction
    val innerRadius = size.minDimension * innerRadiusFraction

    val centerX = size.width / 2
    val centerY = size.height / 2

    var totalAngle = PI / 2 // Since we start at the top center, the initial angle will be 90°
    val degreesPerSection = (2 * PI) / spikes

    moveTo(
        centerX,
        0f,
    ) // Starts at the top center of the bounds

    var x: Double
    var y: Double

    for (i in 1..spikes) {
        // Line going inwards from outerCircle to innerCircle
        totalAngle += degreesPerSection / 2
        x = centerX + cos(totalAngle) * innerRadius
        y = centerY - sin(totalAngle) * innerRadius
        lineTo(
            x.toFloat(),
            y.toFloat(),
        )


        // Line going outwards from innerCircle to outerCircle
        totalAngle += degreesPerSection / 2
        x = centerX + cos(totalAngle) * outerRadius
        y = centerY - sin(totalAngle) * outerRadius
        lineTo(
            x.toFloat(),
            y.toFloat(),
        )
    }

    // Path should be closed to ensure it's not an open shape
    close()

    return this
}
