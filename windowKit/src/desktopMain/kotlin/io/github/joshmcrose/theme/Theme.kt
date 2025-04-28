package io.github.joshmcrose.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun Material3WindowTheme(
    lightColorScheme: ColorScheme = MaterialTheme.colorScheme,
    darkColorScheme: ColorScheme? = null,
    shapes: Shapes = MaterialTheme.shapes,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit
) {
    val isDarkTheme = rememberDarkTheme()
    val colorScheme = darkColorScheme.takeIf { isDarkTheme } ?: lightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
fun MaterialWindowTheme(
    lightColors: Colors = androidx.compose.material.MaterialTheme.colors,
    darkColors: Colors? = null,
    shapes: androidx.compose.material.Shapes = androidx.compose.material.MaterialTheme.shapes,
    typography: androidx.compose.material.Typography = androidx.compose.material.MaterialTheme.typography,
    content: @Composable () -> Unit
) {
    val isDarkTheme = rememberDarkTheme()
    val colors = darkColors.takeIf { isDarkTheme } ?: lightColors
    androidx.compose.material.MaterialTheme(
        colors = colors,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
fun WindowTheme(
    themeColors: ColorSchemes = ColorSchemes(),
    ripple: Indication? = null,
    shapes: ThemeShapes = WindowTheme.shapes,
    typography: ThemeTypography = WindowTheme.typography,
    content: @Composable () -> Unit
) {
    val isDarkTheme = rememberDarkTheme()
    val colorScheme = themeColors.darkTheme.takeIf { isDarkTheme } ?: themeColors.lightTheme
    val textStyle = typography["bodyLarge"] ?: DefaultTextStyle
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalIndication provides (ripple ?: ripple()),
        LocalThemeShapes provides shapes,
        LocalThemeTypography provides typography
    ) {
        ProvideTextStyle(value = textStyle, content = content)
    }
}

object WindowTheme {
    val themeColors: ThemeColors
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    val shapes: ThemeShapes
        @Composable @ReadOnlyComposable get() = LocalThemeShapes.current

    val typography: ThemeTypography
        @Composable @ReadOnlyComposable get() = LocalThemeTypography.current
}