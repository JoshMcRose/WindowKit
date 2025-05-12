package io.github.joshmcrose.titlebar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class TitleBarProperties(
    val height: Dp = 34.dp,
    val includeTitleOnMac: Boolean = false
)
