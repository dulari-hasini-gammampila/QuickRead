package com.example.quickread.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val QuickReadColors = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryDark,
    background = Background,
    onPrimary = TextSecondary,
    onBackground = TextPrimary
)

@Composable
fun QuickReadTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = QuickReadColors,
        typography = Typography,
        content = content
    )
}