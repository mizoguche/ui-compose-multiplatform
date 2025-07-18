package dev.mizoguche.composegram.ui.userprofile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun UserProfileRoute(
    viewModel: UserProfileViewModel,
    onBackClick: () -> Unit,
    isRootScreen: Boolean = false,
) {
    val uiState by viewModel.uiState.collectAsState()

    UserProfileScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        isRootScreen = isRootScreen,
    )
}
