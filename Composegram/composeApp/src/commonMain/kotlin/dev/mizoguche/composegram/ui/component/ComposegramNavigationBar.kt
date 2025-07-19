package dev.mizoguche.composegram.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.mizoguche.composegram.ui.common.ComposegramColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposegramNavigationBar(
    modifier: Modifier = Modifier,
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    scrollBehavior: BottomAppBarScrollBehavior? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val containerColor = ComposegramColors.bottomAppBarContainerColor()
    BottomAppBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = MaterialTheme.colorScheme.contentColorFor(containerColor),
        tonalElevation = tonalElevation,
        windowInsets = WindowInsets.navigationBars,
        scrollBehavior = scrollBehavior,
        content = content,
    )
}
