package dev.mizoguche.composegram.data.repository

import arrow.core.Either
import arrow.core.right
import arrow.core.left
import dev.mizoguche.composegram.domain.post.Post
import dev.mizoguche.composegram.domain.post.PostSummary
import dev.mizoguche.composegram.domain.post.PostError
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.post.PostRepository
import dev.mizoguche.composegram.domain.post.Comment
import dev.mizoguche.composegram.domain.post.CommentId
import dev.mizoguche.composegram.domain.user.User
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.domain.user.Username
import dev.mizoguche.composegram.domain.user.DisplayName
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime

class MockPostRepository: PostRepository {
    private val mockPostSummaries = listOf(
        PostSummary(
            id = PostId("1"),
            body = "美味しいコーヒーを飲みながら仕事中☕️",
            photoUrl = "https://picsum.photos/400/400?random=1",
            user = mockUsers[0],
            createdAt = LocalDateTime.parse("2024-01-15T10:30:00"),
            likeCount = 5,
            commentCount = 2
        ),
        PostSummary(
            id = PostId("2"),
            body = "今日の東京タワーの夜景です！🌃\n綺麗でした✨",
            photoUrl = "https://picsum.photos/400/400?random=2",
            user = mockUsers[1],
            createdAt = LocalDateTime.parse("2024-01-14T20:15:00"),
            likeCount = 12,
            commentCount = 3
        ),
        PostSummary(
            id = PostId("3"),
            body = "新しいレストランでランチ🍝\nパスタが絶品でした！",
            photoUrl = "https://picsum.photos/400/400?random=3",
            user = mockUsers[2],
            createdAt = LocalDateTime.parse("2024-01-14T12:45:00"),
            likeCount = 8,
            commentCount = 5
        ),
        PostSummary(
            id = PostId("4"),
            body = "週末のハイキング🏔️\n自然の中でリフレッシュ！",
            photoUrl = "https://picsum.photos/400/400?random=4",
            user = mockUsers[3],
            createdAt = LocalDateTime.parse("2024-01-13T09:00:00"),
            likeCount = 20,
            commentCount = 8
        ),
        PostSummary(
            id = PostId("5"),
            body = "手作りケーキ完成！🎂\n初めてにしては上出来かな？",
            photoUrl = "https://picsum.photos/400/400?random=5",
            user = mockUsers[4],
            createdAt = LocalDateTime.parse("2024-01-12T16:30:00"),
            likeCount = 15,
            commentCount = 4
        )
    )

    private val mockComments = mapOf(
        PostId("1") to listOf(
            Comment(
                id = CommentId("c1"),
                body = "美味しそう！どこのコーヒーですか？",
                user = mockUsers[1],
                createdAt = LocalDateTime.parse("2024-01-15T10:45:00")
            ),
            Comment(
                id = CommentId("c2"),
                body = "私もコーヒー飲みたくなりました☕",
                user = mockUsers[2],
                createdAt = LocalDateTime.parse("2024-01-15T11:00:00")
            )
        ),
        PostId("2") to listOf(
            Comment(
                id = CommentId("c3"),
                body = "素敵な夜景ですね！",
                user = mockUsers[0],
                createdAt = LocalDateTime.parse("2024-01-14T20:30:00")
            ),
            Comment(
                id = CommentId("c4"),
                body = "東京タワーはいつ見ても綺麗✨",
                user = mockUsers[3],
                createdAt = LocalDateTime.parse("2024-01-14T20:45:00")
            ),
            Comment(
                id = CommentId("c5"),
                body = "今度一緒に行きましょう！",
                user = mockUsers[4],
                createdAt = LocalDateTime.parse("2024-01-14T21:00:00")
            )
        ),
        PostId("3") to listOf(
            Comment(
                id = CommentId("c6"),
                body = "どこのレストランですか？",
                user = mockUsers[0],
                createdAt = LocalDateTime.parse("2024-01-14T13:00:00")
            ),
            Comment(
                id = CommentId("c7"),
                body = "美味しそう〜！",
                user = mockUsers[1],
                createdAt = LocalDateTime.parse("2024-01-14T13:15:00")
            ),
            Comment(
                id = CommentId("c8"),
                body = "今度教えてください！",
                user = mockUsers[3],
                createdAt = LocalDateTime.parse("2024-01-14T13:30:00")
            ),
            Comment(
                id = CommentId("c9"),
                body = "パスタ大好きです🍝",
                user = mockUsers[4],
                createdAt = LocalDateTime.parse("2024-01-14T13:45:00")
            ),
            Comment(
                id = CommentId("c10"),
                body = "予約必要ですか？",
                user = mockUsers[0],
                createdAt = LocalDateTime.parse("2024-01-14T14:00:00")
            )
        ),
        PostId("4") to listOf(
            Comment(
                id = CommentId("c11"),
                body = "どこの山ですか？",
                user = mockUsers[0],
                createdAt = LocalDateTime.parse("2024-01-13T09:30:00")
            ),
            Comment(
                id = CommentId("c12"),
                body = "景色が最高ですね！",
                user = mockUsers[1],
                createdAt = LocalDateTime.parse("2024-01-13T10:00:00")
            ),
            Comment(
                id = CommentId("c13"),
                body = "私もハイキング好きです！",
                user = mockUsers[2],
                createdAt = LocalDateTime.parse("2024-01-13T10:30:00")
            ),
            Comment(
                id = CommentId("c14"),
                body = "天気も良さそうで羨ましい",
                user = mockUsers[4],
                createdAt = LocalDateTime.parse("2024-01-13T11:00:00")
            ),
            Comment(
                id = CommentId("c15"),
                body = "次回は一緒に行きたいです",
                user = mockUsers[0],
                createdAt = LocalDateTime.parse("2024-01-13T11:30:00")
            ),
            Comment(
                id = CommentId("c16"),
                body = "お疲れ様でした！",
                user = mockUsers[1],
                createdAt = LocalDateTime.parse("2024-01-13T12:00:00")
            ),
            Comment(
                id = CommentId("c17"),
                body = "良い運動になりましたね",
                user = mockUsers[2],
                createdAt = LocalDateTime.parse("2024-01-13T12:30:00")
            ),
            Comment(
                id = CommentId("c18"),
                body = "また行きましょう！",
                user = mockUsers[3],
                createdAt = LocalDateTime.parse("2024-01-13T13:00:00")
            )
        ),
        PostId("5") to listOf(
            Comment(
                id = CommentId("c19"),
                body = "すごい！プロみたい！",
                user = mockUsers[0],
                createdAt = LocalDateTime.parse("2024-01-12T17:00:00")
            ),
            Comment(
                id = CommentId("c20"),
                body = "食べたい〜🎂",
                user = mockUsers[1],
                createdAt = LocalDateTime.parse("2024-01-12T17:30:00")
            ),
            Comment(
                id = CommentId("c21"),
                body = "レシピ教えてください！",
                user = mockUsers[2],
                createdAt = LocalDateTime.parse("2024-01-12T18:00:00")
            ),
            Comment(
                id = CommentId("c22"),
                body = "初めてでこのクオリティはすごい！",
                user = mockUsers[3],
                createdAt = LocalDateTime.parse("2024-01-12T18:30:00")
            )
        )
    )

    override suspend fun selectSummaries(): Either<PostError, List<PostSummary>> {
        delay(1000)
        return mockPostSummaries.right()
    }

    override suspend fun findBy(postId: PostId): Either<PostError, Post> {
        delay(500)
        val summary = mockPostSummaries.find { it.id == postId }
            ?: return PostError.NotFound.left()

        val comments = mockComments[postId] ?: emptyList()
        
        val post = Post(
            id = summary.id,
            body = summary.body,
            photoUrl = summary.photoUrl,
            user = summary.user,
            createdAt = summary.createdAt,
            likeCount = summary.likeCount,
            comments = comments
        )
        
        return post.right()
    }
}