package dev.mizoguche.composegram.ui.postdetail

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
import dev.mizoguche.composegram.domain.post.Comment
import dev.mizoguche.composegram.domain.post.Post
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.ui.common.ErrorScreen
import dev.mizoguche.composegram.ui.common.LoadingScreen
import dev.mizoguche.composegram.ui.common.rememberImageLoader
import kotlinx.datetime.LocalDateTime

@Composable
fun PostDetailScreen(
    uiState: PostDetailUiState,
    onBackClick: () -> Unit,
    onUserClick: (UserId) -> Unit,
) {
    when (uiState) {
        PostDetailUiState.Error -> ErrorScreen()
        is PostDetailUiState.Idle -> PostDetailContent(uiState, onBackClick, onUserClick)
        PostDetailUiState.Loading -> LoadingScreen()
    }
}

@Composable
private fun PostDetailContent(
    uiState: PostDetailUiState.Idle,
    onBackClick: () -> Unit,
    onUserClick: (UserId) -> Unit,
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Post header with user info
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clickable { onUserClick(uiState.post.user.id) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = uiState.post.user.photoUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        imageLoader = rememberImageLoader(),
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Column {
                        Text(
                            text = uiState.post.user.username.value,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = uiState.post.user.displayName.value,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            
            // Post image
            item {
                AsyncImage(
                    model = uiState.post.photoUrl,
                    contentDescription = "Post image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Crop,
                    imageLoader = rememberImageLoader()
                )
            }
            
            // Post body and likes
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "❤️ ${uiState.post.likeCount}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = formatDateTime(uiState.post.createdAt),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    Spacer(modifier = Modifier.size(8.dp))
                    
                    Text(
                        text = uiState.post.body,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Spacer(modifier = Modifier.size(16.dp))
                    
                    HorizontalDivider()
                    
                    Spacer(modifier = Modifier.size(8.dp))
                    
                    Text(
                        text = "コメント (${uiState.post.comments.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            // Comments
            items(uiState.post.comments.size) { index ->
                CommentItem(
                    comment = uiState.post.comments[index],
                    onUserClick = onUserClick
                )
            }
        }
    }
}

@Composable
private fun CommentItem(
    comment: Comment,
    onUserClick: (UserId) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = comment.user.photoUrl,
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable { onUserClick(comment.user.id) },
            contentScale = ContentScale.Crop,
            imageLoader = rememberImageLoader(),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = comment.user.username.value,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onUserClick(comment.user.id) }
                )
                Text(
                    text = formatDateTime(comment.createdAt),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.size(4.dp))
            
            Text(
                text = comment.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun formatDateTime(dateTime: LocalDateTime): String {
    return "${dateTime.year}年${dateTime.month}月${dateTime.dayOfMonth}日 ${dateTime.hour}:${
        dateTime.minute.toString().padStart(2, '0')
    }"
}