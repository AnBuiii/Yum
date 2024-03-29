package com.anbui.yum.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.anbui.yum.R

// Set of Material typography styles to start with

val appTextFont = FontFamily(
    Font(R.font.pacifico_regular, FontWeight.Normal)
)

val normal = TextStyle(
    fontSize = 12.sp
)

val semi = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold

)
val Typography = Typography(
    // app text
    displayMedium = TextStyle(
        fontFamily = appTextFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 44.sp,
    ),

    // tab text
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = YumBlack
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),




/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)
