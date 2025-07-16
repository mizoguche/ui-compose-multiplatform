package dev.mizoguche.composegram.ui.startup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import dev.mizoguche.composegram.ui.component.ComposegramButton
import dev.mizoguche.composegram.ui.component.ComposegramCircularProgressIndicator
import dev.mizoguche.composegram.ui.component.ComposegramScaffold
import dev.mizoguche.composegram.ui.component.ComposegramText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StartupScreen(
    uiState: StartupUiState,
    onSignIn: () -> Unit,
) {
    when (uiState) {
        is StartupUiState.Loading -> {
            ComposegramScaffold {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ComposegramCircularProgressIndicator()
                }
            }
        }

        is StartupUiState.Idle -> {
            StartupContent(onSignIn = onSignIn)
        }
    }
}

@Composable
fun StartupContent(onSignIn: () -> Unit) {
    ComposegramScaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ComposegramButton(
                onClick = onSignIn,
            ) {
                ComposegramText("Sign in")
            }
        }
    }
}
