package dev.mizoguche.composegram.ui.userprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.mizoguche.composegram.ui.common.ErrorScreen
import dev.mizoguche.composegram.ui.common.LoadingScreen
import dev.mizoguche.composegram.ui.common.rememberImageLoader

@Composable
fun UserProfileScreen(
    uiState: UserProfileUiState,
    onBackClick: () -> Unit,
) {
    when (uiState) {
        UserProfileUiState.Error -> ErrorScreen()
        is UserProfileUiState.Idle -> UserProfileContent(uiState, onBackClick)
        UserProfileUiState.Loading -> LoadingScreen()
    }
}

@Composable
private fun UserProfileContent(
    uiState: UserProfileUiState.Idle,
    onBackClick: () -> Unit,
) {
    Scaffold {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = uiState.user.photoUrl,
                contentDescription = "Profile picture",
                modifier =
                    Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                contentScale = ContentScale.Crop,
                imageLoader = rememberImageLoader(),
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = uiState.user.displayName.value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = "@${uiState.user.username.value}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
