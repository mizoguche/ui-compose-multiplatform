package dev.mizoguche.composegram.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onLogoutClick: () -> Unit,
) {
    when (uiState) {
        is SettingsUiState.Idle ->
            SettingsContent(
                onLogoutClick = onLogoutClick,
            )
    }
}

@Composable
private fun SettingsContent(onLogoutClick: () -> Unit) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            SettingsItem(
                title = "ログアウト",
                onClick = onLogoutClick,
            )
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    onClick: () -> Unit,
) {
    Text(
        text = title,
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 20.dp),
        style = MaterialTheme.typography.bodyLarge,
    )
}
