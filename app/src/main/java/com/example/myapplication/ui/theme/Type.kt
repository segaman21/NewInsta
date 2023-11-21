package com.example.myapplication.ui.theme

import android.app.Activity
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.myapplication.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
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

private val LocalColorScheme = staticCompositionLocalOf { MyColors }

private val LocalTextStyle = staticCompositionLocalOf { MyTextStyles() }

@Composable
fun FitnessTheme(content: @Composable () -> Unit) {

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = ContextCompat.getColor(window.context, R.color.statusBar)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    CompositionLocalProvider(
        LocalColorScheme provides MyColors, content = content
    )
}

object FitnessTheme {
    val colors: MyColors
        @Composable get() = LocalColorScheme.current

    val textStyles: MyTextStyles
        @Composable get() = LocalTextStyle.current
}

data class MyTextStyles(
    val title: TextStyle = baseTextStyle().copy(
        fontSize = 22.sp, color = Color.Black, fontWeight = FontWeight.W700,
    ),
    val body: TextStyle = baseTextStyle().copy(
        fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.W500
    ),
    val body1: TextStyle = baseTextStyle().copy(
        fontSize = 17.sp, color = Color.Black, fontWeight = FontWeight.W600,
    ),
    val body3: TextStyle = baseTextStyle().copy(
        fontSize = 15.sp, color = Color.Black, fontWeight = FontWeight.W500,
        fontFamily = FontFamily(Font(R.font.medium))
    ),
    val body4: TextStyle = baseTextStyle().copy(
        fontSize = 15.sp, color = Color.Black,
        fontFamily = FontFamily(Font(R.font.regular))
    ),
)

private fun baseTextStyle(): TextStyle {
    return TextStyle(
        fontFamily = fitenssFonts,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Normal,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    )
}

internal val fitenssFonts = FontFamily(
    Font(R.font.regular),
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.medium, FontWeight.Medium)
)

object MyColors {

    val Surface = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Gray3 = Color(0xFF777872)
    val SecondaryBlue = Color(0xBC112A4B)
    val BackgroundChip = Color(0xFFF0F0F0)
    val LightBlue = Color(0xFF4097F4)
    val Green = Color(0xFF3DA241)
}