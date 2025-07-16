package dev.mizoguche.composegram.ui.settings

import androidx.lifecycle.ViewModel

sealed class SettingsUiState {
    data object Idle : SettingsUiState()
}

class SettingsViewModel : ViewModel() {
    val uiState: SettingsUiState = SettingsUiState.Idle
}
