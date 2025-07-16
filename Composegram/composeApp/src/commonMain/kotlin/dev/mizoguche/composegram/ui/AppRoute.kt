package dev.mizoguche.composegram.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable
    data object Startup : AppRoute()

    @Serializable
    data object Main : AppRoute()
}
