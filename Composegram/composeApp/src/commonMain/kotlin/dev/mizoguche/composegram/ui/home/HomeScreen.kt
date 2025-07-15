package dev.mizoguche.composegram.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.mizoguche.composegram.domain.post.PostSummary
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.ui.common.ErrorScreen
import dev.mizoguche.composegram.ui.common.LoadingScreen
import dev.mizoguche.composegram.ui.common.rememberImageLoader
import kotlinx.datetime.LocalDateTime

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSignOut: () -> Unit,
    onUserClick: (UserId) -> Unit,
    onPostClick: (PostId) -> Unit,
) {
    when (uiState) {
        HomeUiState.Empty -> EmptyScreen()
        HomeUiState.Error -> ErrorScreen()
        is HomeUiState.Idle -> HomeContent(uiState, onSignOut, onUserClick, onPostClick)
        HomeUiState.Loading -> LoadingScreen()
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
private fun HomeContent(
    uiState: HomeUiState.Idle,
    onSignOut: () -> Unit,
    onUserClick: (UserId) -> Unit,
    onPostClick: (PostId) -> Unit,
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(uiState.posts.size) { index ->
                PostItem(
                    post = uiState.posts[index],
                    onUserClick = onUserClick,
                    onPostClick = onPostClick
                )
            }
        }
    }
}

@Composable
private fun PostItem(
    post: PostSummary,
    onUserClick: (UserId) -> Unit,
    onPostClick: (PostId) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clickable { onUserClick(post.user.id) },
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
                    .height(400.dp)
                    .clickable { onPostClick(post.id) },
                contentScale = ContentScale.Crop,
                imageLoader = rememberImageLoader()
            )

            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "❤️ ${post.likeCount}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "💬 ${post.commentCount}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { onPostClick(post.id) }
                        )
                    }
                    Text(
                        text = formatDateTime(post.createdAt),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Spacer(modifier = Modifier.size(4.dp))
                
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

private fun formatDateTime(dateTime: LocalDateTime): String {
    return "${dateTime.year}年${dateTime.month}月${dateTime.dayOfMonth}日 ${dateTime.hour}:${
        dateTime.minute.toString().padStart(2, '0')
    }"
}