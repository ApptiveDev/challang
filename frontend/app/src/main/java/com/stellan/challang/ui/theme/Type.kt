package com.stellan.challang.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stellan.challang.R

val PaperlogyFamily = FontFamily(
    Font(R.font.paperlogy_regular, FontWeight.Normal),
    Font(R.font.paperlogy_semibold, FontWeight.SemiBold),
    Font(R.font.paperlogy_bold, FontWeight.Bold),
    Font(R.font.paperlogy_light, FontWeight.Light),
    Font(R.font.paperlogy_thin, FontWeight.Thin),
    Font(R.font.paperlogy_black, FontWeight.Black),
    Font(R.font.paperlogy_extrabold, FontWeight.ExtraBold),
    Font(R.font.paperlogy_extralight, FontWeight.ExtraLight),
    Font(R.font.paperlogy_medium, FontWeight.Medium),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = PaperlogyFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
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