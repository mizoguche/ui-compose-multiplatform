package dev.mizoguche.composegram.ui.settings

import androidx.compose.runtime.Composable

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit,
    onNavigateToStartup: () -> Unit,
) {
    val uiState = viewModel.uiState

    SettingsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onLogoutClick = onNavigateToStartup,
    )
}
