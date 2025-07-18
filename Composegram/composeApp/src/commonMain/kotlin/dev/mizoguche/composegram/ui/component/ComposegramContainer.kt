package dev.mizoguche.composegram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramContainer(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    val defaultBrush = Brush.verticalGradient(
        colors = if (darkTheme) {
            listOf(
                ComposegramColors.lightBlue600,
                ComposegramColors.lightPurple600,
            )
        } else {
            listOf(
                ComposegramColors.lightGreen100,
                ComposegramColors.lightYellow100,
            )
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = defaultBrush),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
        ) {
            content()
        }
    }
}