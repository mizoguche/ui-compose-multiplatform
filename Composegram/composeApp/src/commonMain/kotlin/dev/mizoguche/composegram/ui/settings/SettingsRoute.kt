package dev.mizoguche.composegram.ui.settings

import androidx.compose.runtime.Composable

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel,
    onNavigateToStartup: () -> Unit,
) {
    val uiState = viewModel.uiState

    SettingsScreen(
        uiState = uiState,
        onLogoutClick = onNavigateToStartup,
    )
}
