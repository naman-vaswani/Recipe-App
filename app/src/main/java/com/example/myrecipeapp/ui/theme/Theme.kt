package com.example.myrecipeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// 🎨 Custom Color Palette
private val LightColors = lightColorScheme(
    primary = Color(0xFF6D4C41), // Warm Brown
    secondary = Color(0xFFFFA726), // Orange Accent
    background = Color(0xFFFFF3E0), // Soft Cream
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF8D6E63), // Softer Brown
    secondary = Color(0xFFFFD54F), // Yellow Accent
    background = Color(0xFF212121), // Dark Gray
    surface = Color(0xFF424242), // Slightly lighter gray
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

// 📝 Typography Settings
private val AppTypography = Typography(
    titleLarge = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
    bodyLarge = TextStyle(fontSize = 18.sp),
    bodyMedium = TextStyle(fontSize = 16.sp),
    labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
)

// 🌗 Theme Switcher
@Composable
fun RecipeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Auto-switch
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
