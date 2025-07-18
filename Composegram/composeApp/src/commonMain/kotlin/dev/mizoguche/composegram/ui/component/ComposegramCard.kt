package dev.mizoguche.composegram.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.mizoguche.composegram.ui.common.ComposegramColors

@Composable
fun ComposegramCard(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = ComposegramColors.cardColors(),
        elevation = elevation,
        border = border,
        content = content,
    )
}

@Composable
fun ComposegramCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.shape,
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ComposegramColors.cardColors(),
        elevation = elevation,
        border = border,
        interactionSource = interactionSource,
        content = content,
    )
}

object ComposegramCardDefaults {
    @Composable
    fun cardElevation(
        defaultElevation: androidx.compose.ui.unit.Dp = 1.dp,
        pressedElevation: androidx.compose.ui.unit.Dp = 1.dp,
        focusedElevation: androidx.compose.ui.unit.Dp = 1.dp,
        hoveredElevation: androidx.compose.ui.unit.Dp = 2.dp,
        disabledElevation: androidx.compose.ui.unit.Dp = 0.dp,
    ): CardElevation =
        CardDefaults.cardElevation(
            defaultElevation = defaultElevation,
            pressedElevation = pressedElevation,
            focusedElevation = focusedElevation,
            hoveredElevation = hoveredElevation,
            disabledElevation = disabledElevation,
        )
}
