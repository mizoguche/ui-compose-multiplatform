package dev.mizoguche.composegram.ui.userprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    uiState: UserProfileUiState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("プロフィール") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        when (uiState) {
            UserProfileUiState.Error -> ErrorScreen()
            is UserProfileUiState.Idle -> UserProfileContent(uiState, paddingValues)
            UserProfileUiState.Loading -> LoadingScreen()
        }
    }
}

@Composable
private fun UserProfileContent(
    uiState: UserProfileUiState.Idle,
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
