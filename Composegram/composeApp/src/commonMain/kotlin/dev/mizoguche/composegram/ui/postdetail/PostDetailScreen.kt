package dev.mizoguche.composegram.ui.postdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.mizoguche.composegram.domain.post.Comment
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.ui.common.ErrorScreen
import dev.mizoguche.composegram.ui.common.LoadingScreen
import dev.mizoguche.composegram.ui.common.rememberImageLoader
import dev.mizoguche.composegram.ui.component.ComposegramHorizontalDivider
import dev.mizoguche.composegram.ui.component.ComposegramIcon
import dev.mizoguche.composegram.ui.component.ComposegramIconButton
import dev.mizoguche.composegram.ui.component.ComposegramScaffold
import dev.mizoguche.composegram.ui.component.ComposegramText
import dev.mizoguche.composegram.ui.component.ComposegramTheme
import dev.mizoguche.composegram.ui.component.ComposegramTopAppBar
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    uiState: PostDetailUiState,
    onBackClick: () -> Unit,
    onUserClick: (UserId) -> Unit,
) {
    ComposegramScaffold(
        topBar = {
            ComposegramTopAppBar(
                title = { ComposegramText("投稿") },
                navigationIcon = {
                    ComposegramIconButton(onClick = onBackClick) {
                        ComposegramIcon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        when (uiState) {
            PostDetailUiState.Error -> ErrorScreen()
            is PostDetailUiState.Idle -> PostDetailContent(uiState, paddingValues, onUserClick)
            PostDetailUiState.Loading -> LoadingScreen()
        }
    }
}

@Composable
private fun PostDetailContent(
    uiState: PostDetailUiState.Idle,
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
    onUserClick: (UserId) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
    ) {
        // Post header with user info
        item {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clickable { onUserClick(uiState.post.user.id) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = uiState.post.user.photoUrl,
                    contentDescription = "Profile picture",
                    modifier =
                        Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    imageLoader = rememberImageLoader(),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    ComposegramText(
                        text = uiState.post.user.username.value,
                        style = ComposegramTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    ComposegramText(
                        text = uiState.post.user.displayName.value,
                        style = ComposegramTheme.typography.bodyMedium,
                        color = ComposegramTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        // Post image
        item {
            AsyncImage(
                model = uiState.post.photoUrl,
                contentDescription = "Post image",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                contentScale = ContentScale.Crop,
                imageLoader = rememberImageLoader(),
            )
        }

        // Post body and likes
        item {
            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    ComposegramText(
                        text = "❤️ ${uiState.post.likeCount}",
                        style = ComposegramTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    ComposegramText(
                        text = formatDateTime(uiState.post.createdAt),
                        style = ComposegramTheme.typography.bodyMedium,
                        color = ComposegramTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                ComposegramText(
                    text = uiState.post.body,
                    style = ComposegramTheme.typography.bodyLarge,
                )

                Spacer(modifier = Modifier.size(16.dp))

                ComposegramHorizontalDivider()

                Spacer(modifier = Modifier.size(8.dp))

                ComposegramText(
                    text = "コメント (${uiState.post.comments.size})",
                    style = ComposegramTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        // Comments
        items(uiState.post.comments.size) { index ->
            CommentItem(
                comment = uiState.post.comments[index],
                onUserClick = onUserClick,
            )
        }
    }
}

@Composable
private fun CommentItem(
    comment: Comment,
    onUserClick: (UserId) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        AsyncImage(
            model = comment.user.photoUrl,
            contentDescription = "Profile picture",
            modifier =
                Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { onUserClick(comment.user.id) },
            contentScale = ContentScale.Crop,
            imageLoader = rememberImageLoader(),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ComposegramText(
                    text = comment.user.username.value,
                    style = ComposegramTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onUserClick(comment.user.id) },
                )
                ComposegramText(
                    text = formatDateTime(comment.createdAt),
                    style = ComposegramTheme.typography.bodySmall,
                    color = ComposegramTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.size(4.dp))

            ComposegramText(
                text = comment.body,
                style = ComposegramTheme.typography.bodyMedium,
            )
        }
    }
}

private fun formatDateTime(dateTime: LocalDateTime): String {
    return "${dateTime.year}年${dateTime.month}月${dateTime.dayOfMonth}日 ${dateTime.hour}:${
        dateTime.minute.toString().padStart(2, '0')
    }"
}
