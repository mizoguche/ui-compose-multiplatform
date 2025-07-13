package dev.mizoguche.composegram.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigateToStartup: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onSignOut = onNavigateToStartup,
    )
}