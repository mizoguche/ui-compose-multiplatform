package dev.mizoguche.composegram.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.post.PostSummary
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.ui.common.ErrorScreen
import dev.mizoguche.composegram.ui.common.LoadingScreen
import dev.mizoguche.composegram.ui.common.rememberImageLoader
import dev.mizoguche.composegram.ui.component.ComposegramCard
import dev.mizoguche.composegram.ui.component.ComposegramCardDefaults
import dev.mizoguche.composegram.ui.component.ComposegramIcon
import dev.mizoguche.composegram.ui.component.ComposegramIconButton
import dev.mizoguche.composegram.ui.component.ComposegramScaffold
import dev.mizoguche.composegram.ui.component.ComposegramText
import dev.mizoguche.composegram.ui.component.ComposegramTheme
import dev.mizoguche.composegram.ui.component.ComposegramTopAppBar
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSettingsClick: () -> Unit,
    onUserClick: (UserId) -> Unit,
    onPostClick: (PostId) -> Unit,
    bottomNavPadding: PaddingValues,
) {
    ComposegramScaffold(
        topBar = {
            ComposegramTopAppBar(
                title = { ComposegramText("Composegram") },
                actions = {
                    ComposegramIconButton(onClick = onSettingsClick) {
                        ComposegramIcon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "設定",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        val layoutDirection = LocalLayoutDirection.current
        val padding = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection) + bottomNavPadding.calculateStartPadding(
                layoutDirection,
            ),
            top = paddingValues.calculateTopPadding() + bottomNavPadding.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDirection) + bottomNavPadding.calculateEndPadding(
                layoutDirection,
            ),
            bottom = paddingValues.calculateBottomPadding() + bottomNavPadding.calculateBottomPadding(),
        )
        when (uiState) {
            HomeUiState.Empty -> EmptyContent(padding)
            HomeUiState.Error -> ErrorScreen()
            is HomeUiState.Idle -> HomeContent(uiState, padding, onUserClick, onPostClick)
            HomeUiState.Loading -> LoadingScreen()
        }
    }
}

@Composable
private fun EmptyContent(paddingValues: PaddingValues) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ComposegramText(
            text = "投稿がありません",
            style = ComposegramTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.size(16.dp))
        ComposegramText(
            text = "最初の投稿を作成してみましょう",
            style = ComposegramTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState.Idle,
    paddingValues: PaddingValues,
    onUserClick: (UserId) -> Unit,
    onPostClick: (PostId) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
    ) {
        items(uiState.posts.size) { index ->
            PostItem(
                post = uiState.posts[index],
                onUserClick = onUserClick,
                onPostClick = onPostClick,
            )
        }
    }
}

@Composable
private fun PostItem(
    post: PostSummary,
    onUserClick: (UserId) -> Unit,
    onPostClick: (PostId) -> Unit,
) {
    ComposegramCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        elevation = ComposegramCardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .clickable { onUserClick(post.user.id) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = post.user.photoUrl,
                    contentDescription = "Profile picture",
                    modifier =
                        Modifier
                            .size(32.dp)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    imageLoader = rememberImageLoader(),
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    ComposegramText(
                        text = post.user.username.value,
                        style = ComposegramTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    ComposegramText(
                        text = post.user.displayName.value,
                        style = ComposegramTheme.typography.bodySmall,
                    )
                }
            }

            AsyncImage(
                model = post.photoUrl,
                contentDescription = "Post image",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clickable { onPostClick(post.id) },
                contentScale = ContentScale.Crop,
                imageLoader = rememberImageLoader(),
            )

            Column(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        ComposegramText(
                            text = "❤️ ${post.likeCount}",
                            style = ComposegramTheme.typography.bodyMedium,
                        )
                        ComposegramText(
                            text = "💬 ${post.commentCount}",
                            style = ComposegramTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { onPostClick(post.id) },
                        )
                    }
                    ComposegramText(
                        text = formatDateTime(post.createdAt),
                        style = ComposegramTheme.typography.bodySmall,
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))

                ComposegramText(
                    text = post.body,
                    style = ComposegramTheme.typography.bodyMedium,
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
