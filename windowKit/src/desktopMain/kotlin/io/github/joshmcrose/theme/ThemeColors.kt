package io.github.joshmcrose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.persistentMapOf

@Immutable
data class ColorSchemes(
    val lightTheme: ThemeColors = DefaultLightThemeColors,
    val darkTheme: ThemeColors? = null
)

typealias ThemeColors = Map<String, Color>
internal val LocalColorScheme = staticCompositionLocalOf { DefaultLightThemeColors }

val DefaultLightThemeColors: ThemeColors = persistentMapOf(
    "primaryLight" to Color(0xFF556523),
    "onPrimaryLight" to Color(0xFFFFFFFF),
    "primaryContainerLight" to Color(0xFFD8EB9A),
    "onPrimaryContainerLight" to Color(0xFF3E4C0C),
    "secondaryLight" to Color(0xFF5B6146),
    "onSecondaryLight" to Color(0xFFFFFFFF),
    "secondaryContainerLight" to Color(0xFFE0E6C4),
    "onSecondaryContainerLight" to Color(0xFF434930),
    "tertiaryLight" to Color(0xFF3A665E),
    "onTertiaryLight" to Color(0xFFFFFFFF),
    "tertiaryContainerLight" to Color(0xFFBCECE2),
    "onTertiaryContainerLight" to Color(0xFF204E47),
    "errorLight" to Color(0xFFBA1A1A),
    "onErrorLight" to Color(0xFFFFFFFF),
    "errorContainerLight" to Color(0xFFFFDAD6),
    "onErrorContainerLight" to Color(0xFF93000A),
    "backgroundLight" to Color(0xFFFBFAED),
    "onBackgroundLight" to Color(0xFF1B1C15),
    "surfaceLight" to Color(0xFFFBFAED),
    "onSurfaceLight" to Color(0xFF1B1C15),
    "surfaceVariantLight" to Color(0xFFE3E4D3),
    "onSurfaceVariantLight" to Color(0xFF46483C),
    "outlineLight" to Color(0xFF76786B),
    "outlineVariantLight" to Color(0xFFC7C8B8),
    "scrimLight" to Color(0xFF000000),
    "inverseSurfaceLight" to Color(0xFF303129),
    "inverseOnSurfaceLight" to Color(0xFFF2F1E5),
    "inversePrimaryLight" to Color(0xFFBCCF81),
    "surfaceDimLight" to Color(0xFFDBDBCF),
    "surfaceBrightLight" to Color(0xFFFBFAED),
    "surfaceContainerLowestLight" to Color(0xFFFFFFFF),
    "surfaceContainerLowLight" to Color(0xFFF5F4E8),
    "surfaceContainerLight" to Color(0xFFEFEEE2),
    "surfaceContainerHighLight" to Color(0xFFE9E9DD),
    "surfaceContainerHighestLight" to Color(0xFFE4E3D7),
    "desktop_light_close" to Color(red = 255, green = 59, blue = 48),
    "desktop_light_maximize" to Color(red = 52, green = 199, blue = 89),
    "desktop_light_minimize" to Color(red = 255, green = 204, blue = 0)
)

val DefaultDarkThemeColors: ThemeColors = persistentMapOf(
    "primaryDark" to Color(0xFFBCCF81),
    "onPrimaryDark" to Color(0xFF293500),
    "primaryContainerDark" to Color(0xFF3E4C0C),
    "onPrimaryContainerDark" to Color(0xFFD8EB9A),
    "secondaryDark" to Color(0xFFC4CAA9),
    "onSecondaryDark" to Color(0xFF2D331C),
    "secondaryContainerDark" to Color(0xFF434930),
    "onSecondaryContainerDark" to Color(0xFFE0E6C4),
    "tertiaryDark" to Color(0xFFA1D0C6),
    "onTertiaryDark" to Color(0xFF033730),
    "tertiaryContainerDark" to Color(0xFF204E47),
    "onTertiaryContainerDark" to Color(0xFFBCECE2),
    "errorDark" to Color(0xFFFFB4AB),
    "onErrorDark" to Color(0xFF690005),
    "errorContainerDark" to Color(0xFF93000A),
    "onErrorContainerDark" to Color(0xFFFFDAD6),
    "backgroundDark" to Color(0xFF13140D),
    "onBackgroundDark" to Color(0xFFE4E3D7),
    "surfaceDark" to Color(0xFF13140D),
    "onSurfaceDark" to Color(0xFFE4E3D7),
    "surfaceVariantDark" to Color(0xFF46483C),
    "onSurfaceVariantDark" to Color(0xFFC7C8B8),
    "outlineDark" to Color(0xFF909284),
    "outlineVariantDark" to Color(0xFF46483C),
    "scrimDark" to Color(0xFF000000),
    "inverseSurfaceDark" to Color(0xFFE4E3D7),
    "inverseOnSurfaceDark" to Color(0xFF303129),
    "inversePrimaryDark" to Color(0xFF556523),
    "surfaceDimDark" to Color(0xFF13140D),
    "surfaceBrightDark" to Color(0xFF393A32),
    "surfaceContainerLowestDark" to Color(0xFF0D0F08),
    "surfaceContainerLowDark" to Color(0xFF1B1C15),
    "surfaceContainerDark" to Color(0xFF1F2019),
    "surfaceContainerHighDark" to Color(0xFF292B23),
    "surfaceContainerHighestDark" to Color(0xFF34352D),
    "desktop_dark_close" to Color(red = 255, green = 69, blue = 58),
    "desktop_dark_maximize" to Color(red = 48, green = 240, blue = 88),
    "desktop_dark_minimize" to Color(red = 255, green = 212, blue = 38)
)