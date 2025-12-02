package com.recurt.core.common.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface AppThemeConfig {
    val appName: String
    val primaryColor: Color
    val secondaryColor: Color
    val primaryDarkColor: Color
    val secondaryDarkColor: Color
}

val LocalAppThemeConfig = compositionLocalOf<AppThemeConfig> {
    error("No AppThemeConfig provided")
}