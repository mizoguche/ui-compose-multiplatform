package dev.mizoguche.composegram.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramCircularProgressIndicator(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = ComposegramColors.circularProgressIndicatorColor(),
        strokeWidth = strokeWidth,
        trackColor = ComposegramColors.circularProgressIndicatorTrackColor(),
        strokeCap = strokeCap,
    )
}

@Composable
fun ComposegramCircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
) {
    CircularProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = ComposegramColors.circularProgressIndicatorColor(),
        strokeWidth = strokeWidth,
        trackColor = ComposegramColors.circularProgressIndicatorTrackColor(),
        strokeCap = strokeCap,
    )
}
