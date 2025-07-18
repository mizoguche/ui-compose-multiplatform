package dev.mizoguche.composegram.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ComposegramColors {
    val Purple80 = Color(0xFFD0BCFF)
    val PurpleGrey80 = Color(0xFFCCC2DC)
    val Pink80 = Color(0xFFEFB8C8)

    val Purple40 = Color(0xFF6650a4)
    val PurpleGrey40 = Color(0xFF625b71)
    val Pink40 = Color(0xFF7D5260)

    val DarkColorScheme = darkColorScheme(
        primary = Purple80,
        secondary = PurpleGrey80,
        tertiary = Pink80
    )

    val LightColorScheme = lightColorScheme(
        primary = Purple40,
        secondary = PurpleGrey40,
        tertiary = Pink40

        /* Other default colors to override
        background = Color(0xFFFFFBFE),
        surface = Color(0xFFFFFBFE),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onBackground = Color(0xFF1C1B1F),
        onSurface = Color(0xFF1C1B1F),
        */
    )

    @Composable
    fun buttonColors(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): ButtonColors {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return ButtonDefaults.buttonColors()
    }

    @Composable
    fun cardColors(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): CardColors {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return CardDefaults.cardColors()
    }

    @Composable
    fun iconButtonColors(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): IconButtonColors {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return IconButtonDefaults.iconButtonColors()
    }

    @Composable
    fun navigationBarItemColors(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): NavigationBarItemColors {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return NavigationBarItemDefaults.colors()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topAppBarColors(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): TopAppBarColors {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return TopAppBarDefaults.topAppBarColors()
    }

    @Composable
    fun circularProgressIndicatorColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return ProgressIndicatorDefaults.circularColor
    }

    @Composable
    fun circularProgressIndicatorTrackColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return ProgressIndicatorDefaults.circularTrackColor
    }

    @Composable
    fun dividerColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return DividerDefaults.color
    }

    @Composable
    fun navigationBarContainerColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return NavigationBarDefaults.containerColor
    }

    @Composable
    fun scaffoldContainerColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return colorScheme.background
    }

    @Composable
    fun iconTintColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return LocalContentColor.current
    }

    @Composable
    fun textColor(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): Color {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return Color.Unspecified
    }
}