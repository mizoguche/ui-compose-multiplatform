package dev.mizoguche.composegram.ui.component

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = ComposegramColors.dividerColor(),
    )
}
