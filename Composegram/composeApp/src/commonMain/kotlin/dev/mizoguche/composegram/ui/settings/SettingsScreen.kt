package dev.mizoguche.composegram.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mizoguche.composegram.ui.component.ComposegramScaffold
import dev.mizoguche.composegram.ui.component.ComposegramText
import dev.mizoguche.composegram.ui.component.ComposegramTheme
import dev.mizoguche.composegram.ui.component.ComposegramTopAppBar

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    when (uiState) {
        is SettingsUiState.Idle ->
            SettingsContent(
                onBackClick = onBackClick,
                onLogoutClick = onLogoutClick,
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsContent(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    ComposegramScaffold(
        topBar = {
            ComposegramTopAppBar(
                title = { ComposegramText("設定") },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
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
    ComposegramText(
        text = title,
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 20.dp),
        style = ComposegramTheme.typography.bodyLarge,
    )
}
