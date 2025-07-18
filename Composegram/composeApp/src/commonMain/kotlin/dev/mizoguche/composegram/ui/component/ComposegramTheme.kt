package dev.mizoguche.composegram.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    shapes: Shapes = MaterialTheme.shapes,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit,
) {
    val defaultColorScheme =
        when {
            darkTheme -> ComposegramColors.DarkColorScheme
            else -> ComposegramColors.LightColorScheme
        }

    MaterialTheme(
        colorScheme = defaultColorScheme,
        shapes = shapes,
        typography = typography,
        content = content,
    )
}

object ComposegramTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}
