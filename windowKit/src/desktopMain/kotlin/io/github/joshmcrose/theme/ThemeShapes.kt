package io.github.joshmcrose.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

internal val LocalThemeShapes = staticCompositionLocalOf { DefaultShapes }

fun shapes(
    windowShape: Shape = DefaultShapes.windowShape,
    extraSmall: Shape = DefaultShapes.extraSmall,
    small: Shape = DefaultShapes.small,
    medium: Shape = DefaultShapes.medium,
    large: Shape = DefaultShapes.large,
    extraLarge: Shape = DefaultShapes.extraLarge,
    themeShapes: ImmutableMap<String, Shape> = DefaultShapes.themeShapes
) = ThemeShapes(
    windowShape = windowShape,
    extraSmall = extraSmall,
    small = small,
    medium = medium,
    large = large,
    extraLarge = extraLarge,
    themeShapes = themeShapes
)

val DefaultShapes = ThemeShapes()

@Immutable
data class ThemeShapes (
    val windowShape: Shape = RoundedCornerShape(8.dp),

    val extraSmall: Shape = RoundedCornerShape(4.dp),
    val small: Shape = RoundedCornerShape(8.dp),
    val medium: Shape = RoundedCornerShape(12.dp),
    val large: Shape = RoundedCornerShape(16.dp),
    val extraLarge: Shape = RoundedCornerShape(28.dp),

    val themeShapes: ImmutableMap<String, Shape> = persistentMapOf()
)