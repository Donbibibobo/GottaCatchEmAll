package com.recurt.core.common.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun EncyclopediaTheme(
    config: AppThemeConfig,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val lightColorScheme = lightColorScheme(
        primary = config.primaryColor,
        onPrimary = Color.White,
        primaryContainer = config.primaryColor,
        secondary = config.secondaryColor,
        background = Color(0xFFF5F5F5),
        surface = Color.White,
        onSurface = Color.Black
    )

    val darkColorScheme = darkColorScheme(
        primary = config.primaryDarkColor,
        onPrimary = Color.White,
        primaryContainer = config.primaryDarkColor,
        secondary = config.secondaryDarkColor,
        background = Color(0xFF1C1C1C),
        surface = Color(0xFF2C2C2C),
        onSurface = Color.White
    )

    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(LocalAppThemeConfig provides config) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}