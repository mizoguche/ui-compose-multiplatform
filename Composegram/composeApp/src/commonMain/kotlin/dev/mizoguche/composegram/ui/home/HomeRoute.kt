package dev.mizoguche.composegram.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.user.UserId

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigateToSettings: () -> Unit,
    onNavigateToUserProfile: (UserId) -> Unit,
    onNavigateToPostDetail: (PostId) -> Unit,
    bottomNavPadding: PaddingValues,
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onSettingsClick = onNavigateToSettings,
        onUserClick = onNavigateToUserProfile,
        onPostClick = onNavigateToPostDetail,
        bottomNavPadding = bottomNavPadding,
    )
}
