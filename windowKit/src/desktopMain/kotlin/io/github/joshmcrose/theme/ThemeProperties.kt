package io.github.joshmcrose.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ripple
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
@Immutable
data class ThemeProperties(
    val colorSchemes: ColorSchemes = ColorSchemes(),
    val rippleIndication: Indication = ripple(),
    val textSelectionColors: TextSelectionColors? = null,
    val shapes: ThemeShapes = DefaultShapes,
    val typography: ThemeTypography = DefaultTypography
)
