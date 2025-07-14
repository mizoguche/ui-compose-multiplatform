package dev.mizoguche.composegram.domain.user

value class UserId(val value: String)
value class Username(val value: String)
value class DisplayName(val value: String)

data class User(
    val id: UserId,
    val username: Username,
    val displayName: DisplayName,
    val photoUrl: String,
)