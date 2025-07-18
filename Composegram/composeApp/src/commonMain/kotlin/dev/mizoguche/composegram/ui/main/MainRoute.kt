package dev.mizoguche.composegram.ui.main

import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.user.UserId
import kotlinx.serialization.Serializable

@Serializable
sealed class MainRoute {
    @Serializable
    data object Home : MainRoute()

    @Serializable
    data class UserProfile(val userId: UserId) : MainRoute()

    @Serializable
    data object MyProfile : MainRoute()

    @Serializable
    data class PostDetail(val postId: PostId) : MainRoute()

    @Serializable
    data object Settings : MainRoute()
}
