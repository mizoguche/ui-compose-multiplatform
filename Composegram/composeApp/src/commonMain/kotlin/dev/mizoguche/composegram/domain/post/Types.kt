package dev.mizoguche.composegram.domain.post

import dev.mizoguche.composegram.domain.user.User
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class PostId(val value: String) {
    override fun toString(): String = value
}

value class CommentId(val value: String)

data class PostSummary(
    val id: PostId,
    val body: String,
    val photoUrl: String,
    val user: User,
    val createdAt: LocalDateTime,
    val likeCount: Int,
    val commentCount: Int,
)

data class Comment(
    val id: CommentId,
    val body: String,
    val user: User,
    val createdAt: LocalDateTime,
)

data class Post(
    val id: PostId,
    val body: String,
    val photoUrl: String,
    val user: User,
    val createdAt: LocalDateTime,
    val likeCount: Int,
    val comments: List<Comment>,
)
