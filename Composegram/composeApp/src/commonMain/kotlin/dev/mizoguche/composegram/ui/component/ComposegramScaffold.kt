package dev.mizoguche.composegram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramScaffold(
    modifier: Modifier = Modifier,
    backgroundBrush: Brush? = null,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit,
) {
    val darkTheme = isSystemInDarkTheme()
    val defaultBrush = Brush.verticalGradient(
        colors = if (darkTheme) {
            listOf(
                ComposegramColors.lightBlue600,
                ComposegramColors.lightPurple600
            )
        } else {
            listOf(
                ComposegramColors.lightGreen100,
                ComposegramColors.lightYellow100,
            )
        }
    )
    
    Box(
        modifier = modifier.background(brush = backgroundBrush ?: defaultBrush)
    ) {
        Scaffold(
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = snackbarHost,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition,
            containerColor = Color.Transparent,
            contentColor = contentColorFor(Color.Transparent),
            contentWindowInsets = contentWindowInsets,
            content = content,
        )
    }
}
