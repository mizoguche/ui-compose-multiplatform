package dev.mizoguche.composegram.domain.user

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class UserId(val value: String) {
    override fun toString(): String = value
}

@Serializable
value class Username(val value: String)

@Serializable
value class DisplayName(val value: String)

data class User(
    val id: UserId,
    val username: Username,
    val displayName: DisplayName,
    val photoUrl: String,
)