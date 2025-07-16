package dev.mizoguche.composegram.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import dev.mizoguche.composegram.ui.component.ComposegramCircularProgressIndicator
import dev.mizoguche.composegram.ui.component.ComposegramTheme
import dev.mizoguche.composegram.ui.component.ComposegramText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ComposegramText(
            text = "エラーが発生しました",
            style = ComposegramTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.size(16.dp))
        ComposegramText(
            text = "しばらくしてから再度お試しください",
            style = ComposegramTheme.typography.bodyMedium,
            color = ComposegramTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ComposegramCircularProgressIndicator()
    }
}
