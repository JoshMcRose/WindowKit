package io.github.joshmcrose.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
internal fun WindowTheme(
    colorTheme: ColorScheme = MaterialTheme.colorScheme,
    shapes: Shapes = MaterialTheme.shapes,
    typography: Typography = MaterialTheme.typography,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorTheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
internal fun WindowTheme(
    colorTheme: Colors = androidx.compose.material.MaterialTheme.colors,
    shapes: androidx.compose.material.Shapes = androidx.compose.material.MaterialTheme.shapes,
    typography: androidx.compose.material.Typography = androidx.compose.material.MaterialTheme.typography,
    content: @Composable () -> Unit
) {
    androidx.compose.material.MaterialTheme(
        colors = colorTheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
internal fun WindowTheme(
    colorTheme: ThemeColors = DefaultLightThemeColors,
    ripple: Indication? = null,
    shapes: ThemeShapes = WindowTheme.shapes,
    typography: ThemeTypography = WindowTheme.typography,
    content: @Composable () -> Unit
) {
    val localTextSelectionColors = LocalTextSelectionColors.current
    val selectionColors = remember { colorTheme.textSelectionColors ?: localTextSelectionColors }
    CompositionLocalProvider(
        LocalColorScheme provides colorTheme,
        LocalIndication provides (ripple ?: ripple()),
        LocalThemeShapes provides shapes,
        LocalTextSelectionColors provides selectionColors,
        LocalThemeTypography provides typography
    ) {
        ProvideTextStyle(value = typography.defaultTextStyle, content = content)
    }
}

object WindowTheme {
    val colors: ThemeColors
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    val shapes: ThemeShapes
        @Composable @ReadOnlyComposable get() = LocalThemeShapes.current

    val typography: ThemeTypography
        @Composable @ReadOnlyComposable get() = LocalThemeTypography.current
}