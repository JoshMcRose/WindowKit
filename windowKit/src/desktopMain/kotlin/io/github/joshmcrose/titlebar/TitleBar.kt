package io.github.joshmcrose.titlebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import io.github.joshmcrose.theme.WindowTheme.colors
import io.github.joshmcrose.window.WindowProperties
import org.jetbrains.skiko.hostOs

@Composable
fun WindowScope.TitleBar(
    title: String,
    windowState: WindowState,
    windowProperties: WindowProperties,
    titleBarProperties: TitleBarProperties,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    windowSizeCallback: (Int) -> Unit
) {
    val hostOs by remember { derivedStateOf { hostOs.isMacOS } }

    var parentSize by remember { mutableStateOf(IntSize.Zero) }
    var buttonsRect by remember { mutableStateOf(Rect.Zero) }
    var titleRect by remember { mutableStateOf(Rect(0f, 1f, 1f, 0f)) }
    val titleOverlapsButtons by remember(titleRect) {
        derivedStateOf { titleRect overlaps buttonsRect }
    }

    val windowFullscreen by remember(windowState.placement) {
        derivedStateOf { windowState.placement.resize(WindowPlacement.Fullscreen) }
    }

    val windowMaximized by remember(windowState.placement) {
        derivedStateOf { windowState.placement.resize(WindowPlacement.Maximized) }
    }

    var buttonsWidth by remember { mutableStateOf(0) }
    val draggableWidth by remember(parentSize) { derivedStateOf { parentSize.width - buttonsWidth } }
    val draggableWidthAsDP = with(LocalDensity.current) { draggableWidth.toDp() }
    val onMacDrawTitle = if (hostOs) titleBarProperties.includeTitleOnMac else true

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(titleBarProperties.height)
            .background(colors.titlebarBackground)
            .onGloballyPositioned { parentSize = it.size }
            .drawTitle(
                title = title,
                parentSize = parentSize,
                draw = !titleOverlapsButtons && onMacDrawTitle,
                titleRect = { titleRect = it }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hostOs) MacTitleButtons(
            windowState = windowState,
            windowProperties = windowProperties,
            onClose = onClose,
            onMinimize = { windowState.isMinimized = !windowState.isMinimized },
            onAdjustSize = windowProperties.ifResizable { windowState.placement = windowFullscreen },
            modifier = modifier.width(IntrinsicSize.Max)
                .onGloballyPositioned {
                    buttonsRect = it.boundsInRoot()
                    buttonsWidth = it.size.width
                    windowSizeCallback(buttonsWidth)
                }
        )

        DraggableArea(
            modifier = Modifier.fillMaxHeight().width(draggableWidthAsDP),
            adjustSize = windowProperties.ifResizable { windowState.placement = windowMaximized }
        )

        if (!hostOs) WindowsTitleButtons(
            windowProperties = windowProperties,
            onClose = onClose,
            onMinimize = { windowState.isMinimized = !windowState.isMinimized },
            onAdjustSize = windowProperties.ifResizable { windowState.placement = windowMaximized },
            modifier = modifier.width(IntrinsicSize.Min)
                .onGloballyPositioned {
                    buttonsRect = it.boundsInRoot()
                    buttonsWidth = WINDOWS_BUTTONS_WIDTH
                    windowSizeCallback(buttonsWidth)
                }
        )
    }
}

private infix fun Rect.overlaps(other: Rect): Boolean =
    this.left <= other.right && this.right >= other.left

private fun WindowPlacement.resize(target: WindowPlacement) =
    if (this == target) WindowPlacement.Floating else target

private fun WindowProperties.ifResizable(block: () -> Unit): (() -> Unit)? =
    if (resizable) block else null