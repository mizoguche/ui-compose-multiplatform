package dev.mizoguche.composegram.domain.post

import arrow.core.Either

sealed class PostError {
    abstract val errorMessage: String

    data object NotFound : PostError() {
        override val errorMessage: String
            get() = "ポストが見つかりませんでした。"
    }

    data object NetworkError : PostError() {
        override val errorMessage: String
            get() = "ネットワークにエラーが発生しました。しばらくしてから再度お試し下さい。"
    }
}

interface PostRepository {
    suspend fun select(): Either<PostError, List<Post>>
    suspend fun findBy(postId: PostId): Either<PostError, Post>
}