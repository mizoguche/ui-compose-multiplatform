package dev.mizoguche.composegram

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.mizoguche.composegram.ui.AppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}