package dev.mizoguche.composegram.ui.startup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun StartupRoute(
    viewModel: StartupViewModel,
    onNavigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.navigationEvent.collect {
            when (it) {
                StartupEvent.NavigateToHome -> onNavigateToHome()
            }
        }
    }

    StartupScreen(
        uiState = uiState,
        onSignIn = viewModel::signIn,
    )
}