package dev.mizoguche.composegram.ui.postdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.mizoguche.composegram.domain.user.UserId

@Composable
fun PostDetailRoute(
    viewModel: PostDetailViewModel,
    onBackClick: () -> Unit,
    onNavigateToUserProfile: (UserId) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    PostDetailScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onUserClick = onNavigateToUserProfile,
    )
}