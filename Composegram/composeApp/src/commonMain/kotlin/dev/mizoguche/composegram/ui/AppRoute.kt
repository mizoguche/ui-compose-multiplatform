package dev.mizoguche.composegram.ui

import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.user.UserId
import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable
    data object Startup : AppRoute()

    @Serializable
    data object Home : AppRoute()

    @Serializable
    data class UserProfile(val userId: UserId) : AppRoute()

    @Serializable
    data class PostDetail(val postId: PostId) : AppRoute()
}
