package dev.mizoguche.composegram.ui

import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.domain.post.PostId
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

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