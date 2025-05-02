package io.github.joshmcrose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.persistentMapOf

@Immutable
data class ColorSchemes(
    val lightTheme: ThemeColors = DefaultLightThemeColors,
    val darkTheme: ThemeColors? = DefaultDarkThemeColors
)

typealias ThemeColors = Map<String, Color>
internal val LocalColorScheme = staticCompositionLocalOf { DefaultLightThemeColors }

val DefaultLightThemeColors: ThemeColors = persistentMapOf(
    "primary" to Color(0xFF556523),
    "onPrimary" to Color(0xFFFFFFFF),
    "primaryContainer" to Color(0xFFD8EB9A),
    "onPrimaryContainer" to Color(0xFF3E4C0C),
    "secondary" to Color(0xFF5B6146),
    "onSecondary" to Color(0xFFFFFFFF),
    "secondaryContainer" to Color(0xFFE0E6C4),
    "onSecondaryContainer" to Color(0xFF434930),
    "tertiary" to Color(0xFF3A665E),
    "onTertiary" to Color(0xFFFFFFFF),
    "tertiaryContainer" to Color(0xFFBCECE2),
    "onTertiaryContainer" to Color(0xFF204E47),
    "error" to Color(0xFFBA1A1A),
    "onError" to Color(0xFFFFFFFF),
    "errorContainer" to Color(0xFFFFDAD6),
    "onErrorContainer" to Color(0xFF93000A),
    "background" to Color(0xFFFBFAED),
    "onBackground" to Color(0xFF1B1C15),
    "surface" to Color(0xFFFBFAED),
    "onSurface" to Color(0xFF1B1C15),
    "surfaceVariant" to Color(0xFFE3E4D3),
    "onSurfaceVariant" to Color(0xFF46483C),
    "outline" to Color(0xFF76786B),
    "outlineVariant" to Color(0xFFC7C8B8),
    "scrim" to Color(0xFF000000),
    "inverseSurface" to Color(0xFF303129),
    "inverseOnSurface" to Color(0xFFF2F1E5),
    "inversePrimary" to Color(0xFFBCCF81),
    "surfaceDim" to Color(0xFFDBDBCF),
    "surfaceBright" to Color(0xFFFBFAED),
    "surfaceContainerLowest" to Color(0xFFFFFFFF),
    "surfaceContainerLow" to Color(0xFFF5F4E8),
    "surfaceContainer" to Color(0xFFEFEEE2),
    "surfaceContainerHigh" to Color(0xFFE9E9DD),
    "surfaceContainerHighest" to Color(0xFFE4E3D7),
    "desktop_close" to Color(red = 255, green = 59, blue = 48),
    "desktop_maximize" to Color(red = 52, green = 199, blue = 89),
    "desktop_minimize" to Color(red = 255, green = 204, blue = 0)
)

val DefaultDarkThemeColors: ThemeColors = persistentMapOf(
    "primary" to Color(0xFFBCCF81),
    "onPrimary" to Color(0xFF293500),
    "primaryContainer" to Color(0xFF3E4C0C),
    "onPrimaryContainer" to Color(0xFFD8EB9A),
    "secondary" to Color(0xFFC4CAA9),
    "onSecondary" to Color(0xFF2D331C),
    "secondaryContainer" to Color(0xFF434930),
    "onSecondaryContainer" to Color(0xFFE0E6C4),
    "tertiary" to Color(0xFFA1D0C6),
    "onTertiary" to Color(0xFF033730),
    "tertiaryContainer" to Color(0xFF204E47),
    "onTertiaryContainer" to Color(0xFFBCECE2),
    "error" to Color(0xFFFFB4AB),
    "onError" to Color(0xFF690005),
    "errorContainer" to Color(0xFF93000A),
    "onErrorContainer" to Color(0xFFFFDAD6),
    "background" to Color(0xFF13140D),
    "onBackground" to Color(0xFFE4E3D7),
    "surface" to Color(0xFF13140D),
    "onSurface" to Color(0xFFE4E3D7),
    "surfaceVariant" to Color(0xFF46483C),
    "onSurfaceVariant" to Color(0xFFC7C8B8),
    "outline" to Color(0xFF909284),
    "outlineVariant" to Color(0xFF46483C),
    "scrim" to Color(0xFF000000),
    "inverseSurface" to Color(0xFFE4E3D7),
    "inverseOnSurface" to Color(0xFF303129),
    "inversePrimary" to Color(0xFF556523),
    "surfaceDim" to Color(0xFF13140D),
    "surfaceBright" to Color(0xFF393A32),
    "surfaceContainerLowest" to Color(0xFF0D0F08),
    "surfaceContainerLow" to Color(0xFF1B1C15),
    "surfaceContainer" to Color(0xFF1F2019),
    "surfaceContainerHigh" to Color(0xFF292B23),
    "surfaceContainerHighest" to Color(0xFF34352D),
    "desktop_close" to Color(red = 255, green = 69, blue = 58),
    "desktop_maximize" to Color(red = 48, green = 240, blue = 88),
    "desktop_minimize" to Color(red = 255, green = 212, blue = 38)
)