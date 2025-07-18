package dev.mizoguche.composegram

import androidx.compose.runtime.Composable
import dev.mizoguche.composegram.ui.AppNavigation
import dev.mizoguche.composegram.ui.component.ComposegramContainer
import dev.mizoguche.composegram.ui.component.ComposegramTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ComposegramTheme {
        ComposegramContainer {
            AppNavigation()
        }
    }
}
