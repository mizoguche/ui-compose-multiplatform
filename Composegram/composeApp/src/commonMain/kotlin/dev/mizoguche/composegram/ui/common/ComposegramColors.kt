package dev.mizoguche.composegram.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ComposegramColors {
    // Light Blue Colors
    val lightBlue100 = Color(0xFFD8F1F7)
    val lightBlue200 = Color(0xFF8CCFDC)
    val lightBlue300 = Color(0xFF70A6B1)
    val lightBlue400 = Color(0xFF558088)
    val lightBlue500 = Color(0xFF3B5B61)
    val lightBlue600 = Color(0xFF23383C)
    val lightBlue700 = Color(0xFF0D191B)

    // Light Pink Colors
    val lightPink100 = Color(0xFFFAEEF1)
    val lightPink200 = Color(0xFFEEBDCC)
    val lightPink300 = Color(0xFFE387A8)
    val lightPink400 = Color(0xFFD04E85)
    val lightPink500 = Color(0xFF983760)
    val lightPink600 = Color(0xFF63213D)
    val lightPink700 = Color(0xFF330D1D)

    // Light Purple Colors
    val lightPurple100 = Color(0xFFE7D3ED)
    val lightPurple200 = Color(0xFFCEA3DA)
    val lightPurple300 = Color(0xFFB672C7)
    val lightPurple400 = Color(0xFF8C519A)
    val lightPurple500 = Color(0xFF5F3669)
    val lightPurple600 = Color(0xFF361C3D)
    val lightPurple700 = Color(0xFF1A0B1E)

    // Light Yellow Colors
    val lightYellow100 = Color(0xFFFBF0D5)
    val lightYellow200 = Color(0xFFF1D475)
    val lightYellow300 = Color(0xFFC5AD5E)
    val lightYellow400 = Color(0xFF9B8849)
    val lightYellow500 = Color(0xFF726434)
    val lightYellow600 = Color(0xFF4D4221)
    val lightYellow700 = Color(0xFF2A230F)

    // Light Green Colors
    val lightGreen100 = Color(0xFFD9F8E3)
    val lightGreen200 = Color(0xFF90DCAC)
    val lightGreen300 = Color(0xFF74B28B)
    val lightGreen400 = Color(0xFF598A6B)
    val lightGreen500 = Color(0xFF3F644D)
    val lightGreen600 = Color(0xFF274030)
    val lightGreen700 = Color(0xFF112016)

    // Light Red Colors
    val lightRed100 = Color(0xFFFCEDED)
    val lightRed200 = Color(0xFFF5BDBC)
    val lightRed300 = Color(0xFFF08583)
    val lightRed400 = Color(0xFFE83F39)
    val lightRed500 = Color(0xFFAA2C27)
    val lightRed600 = Color(0xFF701916)
    val lightRed700 = Color(0xFF3B0907)

    // Grey Colors
    val grey100 = Color(0xFFEBEDED)
    val grey200 = Color(0xFFC0C5C7)
    val grey300 = Color(0xFF9A9EA0)
    val grey400 = Color(0xFF76797A)
    val grey500 = Color(0xFF545657)
    val grey600 = Color(0xFF333536)
    val grey700 = Color(0xFF161718)

    val DarkColorScheme = darkColorScheme(
        primary = lightBlue200,
        secondary = lightPink200,
        tertiary = lightPurple200,
        background = lightBlue600,
        surface = lightBlue500,
        onPrimary = grey500,
        onSecondary = grey500,
        onTertiary = grey500,
        onBackground = grey100,
        onSurface = grey100,
    )

    val LightColorScheme = lightColorScheme(
        primary = lightBlue200,
        secondary = lightPink200,
        tertiary = lightPurple200,
        background = lightGreen200,
        surface = lightRed200,
        onPrimary = grey500,
        onSecondary = grey500,
        onTertiary = grey500,
        onBackground = grey500,
        onSurface = grey500,
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
        return CardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface,
            disabledContainerColor = colorScheme.surfaceVariant,
            disabledContentColor = colorScheme.onSurfaceVariant,
        )
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
        return NavigationBarItemColors(
            selectedIconColor = colorScheme.onPrimary,
            selectedTextColor = colorScheme.onPrimary,
            selectedIndicatorColor = colorScheme.primary,
            unselectedIconColor = colorScheme.onSurfaceVariant,
            unselectedTextColor = colorScheme.onSurfaceVariant,
            disabledIconColor = colorScheme.surfaceDim,
            disabledTextColor = colorScheme.onSurface,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topAppBarColors(
        darkTheme: Boolean = isSystemInDarkTheme()
    ): TopAppBarColors {
        val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
        return TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = colorScheme.onBackground,
            titleContentColor = colorScheme.onBackground,
            actionIconContentColor = colorScheme.onBackground,
        )
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
        return Color.Transparent
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
        return colorScheme.onSurface
    }
}