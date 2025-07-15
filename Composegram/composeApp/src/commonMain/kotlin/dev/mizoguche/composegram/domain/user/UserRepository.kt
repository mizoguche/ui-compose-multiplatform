package dev.mizoguche.composegram.domain.user

import arrow.core.Either

sealed class UserError {
    abstract val errorMessage: String

    data object NotFound : UserError() {
        override val errorMessage: String
            get() = "ユーザーが見つかりませんでした。"
    }

    data object NetworkError : UserError() {
        override val errorMessage: String
            get() = "ネットワークにエラーが発生しました。しばらくしてから再度お試し下さい。"
    }
}

interface UserRepository {
    suspend fun findBy(userId: UserId): Either<UserError, User>
}
