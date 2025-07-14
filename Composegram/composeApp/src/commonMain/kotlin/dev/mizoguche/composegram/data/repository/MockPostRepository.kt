package dev.mizoguche.composegram.data.repository

import arrow.core.Either
import arrow.core.right
import arrow.core.left
import dev.mizoguche.composegram.domain.post.Post
import dev.mizoguche.composegram.domain.post.PostError
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.post.PostRepository
import dev.mizoguche.composegram.domain.user.User
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.domain.user.Username
import dev.mizoguche.composegram.domain.user.DisplayName
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime

class MockPostRepository: PostRepository {
    private val mockPosts = listOf(
        Post(
            id = PostId("1"),
            body = "美味しいコーヒーを飲みながら仕事中☕️",
            photoUrl = "https://picsum.photos/400/400?random=1",
            user = User(
                id = UserId("user1"),
                username = Username("tanaka_taro"),
                displayName = DisplayName("田中太郎"),
                photoUrl = "https://picsum.photos/100/100?random=11"
            ),
            createdAt = LocalDateTime.parse("2024-01-15T10:30:00")
        ),
        Post(
            id = PostId("2"),
            body = "今日の東京タワーの夜景です！🌃\n綺麗でした✨",
            photoUrl = "https://picsum.photos/400/400?random=2",
            user = User(
                id = UserId("user2"),
                username = Username("sato_hanako"),
                displayName = DisplayName("佐藤花子"),
                photoUrl = "https://picsum.photos/100/100?random=12"
            ),
            createdAt = LocalDateTime.parse("2024-01-14T20:15:00")
        ),
        Post(
            id = PostId("3"),
            body = "新しいレストランでランチ🍝\nパスタが絶品でした！",
            photoUrl = "https://picsum.photos/400/400?random=3",
            user = User(
                id = UserId("user3"),
                username = Username("yamada_ichiro"),
                displayName = DisplayName("山田一郎"),
                photoUrl = "https://picsum.photos/100/100?random=13"
            ),
            createdAt = LocalDateTime.parse("2024-01-14T12:45:00")
        ),
        Post(
            id = PostId("4"),
            body = "週末のハイキング🏔️\n自然の中でリフレッシュ！",
            photoUrl = "https://picsum.photos/400/400?random=4",
            user = User(
                id = UserId("user4"),
                username = Username("suzuki_yuki"),
                displayName = DisplayName("鈴木ゆき"),
                photoUrl = "https://picsum.photos/100/100?random=14"
            ),
            createdAt = LocalDateTime.parse("2024-01-13T09:00:00")
        ),
        Post(
            id = PostId("5"),
            body = "手作りケーキ完成！🎂\n初めてにしては上出来かな？",
            photoUrl = "https://picsum.photos/400/400?random=5",
            user = User(
                id = UserId("user5"),
                username = Username("takahashi_mei"),
                displayName = DisplayName("高橋めい"),
                photoUrl = "https://picsum.photos/100/100?random=15"
            ),
            createdAt = LocalDateTime.parse("2024-01-12T16:30:00")
        )
    )

    override suspend fun select(): Either<PostError, List<Post>> {
        delay(1000)
        return mockPosts.right()
    }

    override suspend fun findBy(postId: PostId): Either<PostError, Post> {
        delay(500)
        return mockPosts.find { it.id == postId }
            ?.right()
            ?: PostError.NotFound.left()
    }
}