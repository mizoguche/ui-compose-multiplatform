package dev.mizoguche.composegram.domain.post

import dev.mizoguche.composegram.domain.user.User
import kotlinx.datetime.LocalDateTime

value class PostId(val value: String)

data class Post(
    val id: PostId,
    val body: String,
    val photoUrl: String,
    val user: User,
    val createdAt: LocalDateTime,
)