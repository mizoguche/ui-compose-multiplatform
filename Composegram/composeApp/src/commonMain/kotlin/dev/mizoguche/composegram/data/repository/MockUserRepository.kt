package dev.mizoguche.composegram.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dev.mizoguche.composegram.domain.user.DisplayName
import dev.mizoguche.composegram.domain.user.User
import dev.mizoguche.composegram.domain.user.UserError
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.domain.user.UserRepository
import dev.mizoguche.composegram.domain.user.Username
import kotlinx.coroutines.delay

val mockUsers =
    listOf(
        User(
            id = UserId("user1"),
            username = Username("tanaka_taro"),
            displayName = DisplayName("田中太郎"),
            photoUrl = "https://picsum.photos/100/100?random=11",
        ),
        User(
            id = UserId("user2"),
            username = Username("sato_hanako"),
            displayName = DisplayName("佐藤花子"),
            photoUrl = "https://picsum.photos/100/100?random=12",
        ),
        User(
            id = UserId("user3"),
            username = Username("yamada_ichiro"),
            displayName = DisplayName("山田一郎"),
            photoUrl = "https://picsum.photos/100/100?random=13",
        ),
        User(
            id = UserId("user4"),
            username = Username("suzuki_yuki"),
            displayName = DisplayName("鈴木ゆき"),
            photoUrl = "https://picsum.photos/100/100?random=14",
        ),
        User(
            id = UserId("user5"),
            username = Username("takahashi_mei"),
            displayName = DisplayName("高橋めい"),
            photoUrl = "https://picsum.photos/100/100?random=15",
        ),
    )

class MockUserRepository : UserRepository {
    override suspend fun findBy(userId: UserId): Either<UserError, User> {
        delay(500)
        return mockUsers.find { it.id == userId }
            ?.right()
            ?: UserError.NotFound.left()
    }
}
