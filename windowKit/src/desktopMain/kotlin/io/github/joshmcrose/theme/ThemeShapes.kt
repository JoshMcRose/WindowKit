package io.github.joshmcrose.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

typealias ThemeShapes = ImmutableMap<String, Shape>
internal val LocalThemeShapes = staticCompositionLocalOf { DefaultShapes }

val DefaultShapes: ThemeShapes = persistentMapOf(
    "extraSmall" to RoundedCornerShape(4.0.dp),
    "small" to RoundedCornerShape(8.0.dp),
    "medium" to RoundedCornerShape(12.0.dp),
    "large" to RoundedCornerShape(16.0.dp),
    "extraLarge" to RoundedCornerShape(28.0.dp),
)