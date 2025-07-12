package dev.mizoguche.composegram

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform