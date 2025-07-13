package dev.mizoguche.composegram.ui.startup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
            Scaffold {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        is StartupUiState.Idle -> {
            StartupContent(onSignIn = onSignIn)
        }
    }
}

@Composable
fun StartupContent(
    onSignIn: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = onSignIn,
            ) {
                Text("Sign in")
            }
        }
    }
}