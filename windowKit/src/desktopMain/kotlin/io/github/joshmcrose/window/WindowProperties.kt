package io.github.joshmcrose.window

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.input.key.KeyEvent

@Stable
@Immutable
data class WindowProperties(
    val visible: Boolean = true,
    val resizable: Boolean = true,
    val enabled: Boolean = true,
    val alwaysOnTop: Boolean = false,
    val focusable: Boolean = true,
    val onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    val onKeyEvent: (KeyEvent) -> Boolean = { false },
)
