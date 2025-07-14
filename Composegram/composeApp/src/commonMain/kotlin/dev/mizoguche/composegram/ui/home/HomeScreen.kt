package dev.mizoguche.composegram.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import dev.mizoguche.composegram.domain.post.Post
import dev.mizoguche.composegram.ui.util.rememberImageLoader
import kotlinx.datetime.LocalDateTime

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSignOut: () -> Unit,
) {
    when (uiState) {
        HomeUiState.Empty -> EmptyScreen()
        HomeUiState.Error -> ErrorScreen()
        is HomeUiState.Idle -> HomeContent(uiState, onSignOut)
        HomeUiState.Loading -> LoadingScreen()
    }
}

@Composable
private fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "エラーが発生しました",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "しばらくしてから再度お試しください",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "投稿がありません",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "最初の投稿を作成してみましょう",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState.Idle,
    onSignOut: () -> Unit,
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(uiState.posts.size) { index ->
                PostItem(post = uiState.posts[index])
            }
        }
    }
}

@Composable
private fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = post.user.photoUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    imageLoader = rememberImageLoader(),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        text = post.user.username.value,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = post.user.displayName.value,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            AsyncImage(
                model = post.photoUrl,
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop,
                imageLoader = rememberImageLoader()
            )
            
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    text = formatDateTime(post.createdAt),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun formatDateTime(dateTime: LocalDateTime): String {
    return "${dateTime.year}年${dateTime.monthNumber}月${dateTime.dayOfMonth}日 ${dateTime.hour}:${dateTime.minute.toString().padStart(2, '0')}"
}