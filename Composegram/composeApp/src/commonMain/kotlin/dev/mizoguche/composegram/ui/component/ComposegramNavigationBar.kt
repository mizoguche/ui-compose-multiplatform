package dev.mizoguche.composegram.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramNavigationBar(
    modifier: Modifier = Modifier,
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    content: @Composable RowScope.() -> Unit,
) {
    val containerColor = ComposegramColors.navigationBarContainerColor()
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.contentColorFor(containerColor),
        tonalElevation = tonalElevation,
        windowInsets = WindowInsets.navigationBars,
        content = content,
    )
}
