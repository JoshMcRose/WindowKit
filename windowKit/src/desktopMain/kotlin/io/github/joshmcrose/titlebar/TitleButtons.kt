package io.github.joshmcrose.titlebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import io.github.joshmcrose.theme.WindowTheme.colors
import io.github.joshmcrose.window.WindowProperties
import io.github.joshmcrose.windowkit.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MacTitleButtons(
    windowState: WindowState,
    windowProperties: WindowProperties,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: (() -> Unit)?,
    onMinimize: () -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }

    val isFullScreen by remember(windowState.placement) { mutableStateOf(windowState.placement == WindowPlacement.Fullscreen) }

    Row(
        modifier = modifier.onHover { isHovered = it },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        Spacer(Modifier.width(8.dp))
        MacButton(
            painter = Res.drawable.close,
            contentDescription = stringResource(Res.string.close),
            color = colors.macCloseIconColor,
            isHovered = isHovered,
            onClick = onClose
        )

        MacButton(
            painter = if (isFullScreen) null else Res.drawable.mac_minimize,
            contentDescription = stringResource(Res.string.minimize),
            color = if (isFullScreen) colors.macDisabledToolbarIconColor else colors.macMinimizeIconColor,
            isHovered = isHovered,
            onClick = onMinimize
        )

        MacButton(
            painter = if (isFullScreen) Res.drawable.close_fullscreen else Res.drawable.open_in_full,
            contentDescription =
                stringResource(if (isFullScreen) Res.string.exit_full_screen else Res.string.enter_full_Screen),
            color = if (windowProperties.resizable) colors.macMaximizeIconColor else colors.macDisabledToolbarIconColor,
            enabled = windowProperties.resizable,
            isHovered = isHovered,
            onClick = onAdjustSize ?: {}
        )
        Spacer(Modifier.width(8.dp))
    }
}

const val WINDOWS_BUTTONS_WIDTH = 240

@Composable
fun WindowsTitleButtons(
    windowProperties: WindowProperties,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: (() -> Unit)?,
    onMinimize: () -> Unit
) {
    val minWidthAsDP = with(LocalDensity.current) { WINDOWS_BUTTONS_WIDTH.toDp() }
    Row(
        modifier = modifier.widthIn(minWidthAsDP),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
    ) {
        Spacer(Modifier.width(8.dp))

        WindowsButton(
            painter = painterResource(Res.drawable.minimize),
            contentDescription = stringResource(Res.string.minimize),
            onClick = onMinimize
        )

        WindowsButton(
            enabled = windowProperties.resizable,
            painter = painterResource(Res.drawable.maximize),
            contentDescription = stringResource(Res.string.maximize),
            onClick = onAdjustSize ?: {}
        )

        WindowsButton(
            painter = painterResource(Res.drawable.close),
            contentDescription = stringResource(Res.string.close),
            onClick = onClose
        )

        Spacer(Modifier.width(8.dp))
    }
}

fun Modifier.titleBarButton(
    color: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    clip(CircleShape)
        .size(14.dp)
        .background(color, shape = CircleShape) // TODO: Test this
//        .border(width = 1.dp, color = colorScheme.tertiary.copy(alpha = 0.5f), shape = CircleShape)
        .shadow(elevation = 2.dp, shape = CircleShape, clip = true)
        .clickable(onClick = onClick, enabled = enabled)
}

@Composable
fun MacButton(
    painter: DrawableResource?,
    contentDescription: String?,
    color: Color,
    isHovered: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.titleBarButton(color = color, enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        painter?.let {
            if (isHovered) Icon(painter = painterResource(it), contentDescription = contentDescription)
        }
    }
}

@Composable
fun WindowsButton(
    painter: Painter,
    contentDescription: String?,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.size(24.dp),
        shape = RectangleShape,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            contentColor = colors.windowsToolbarIconColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = colors.windowsToolbarIconColor.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(4.dp),
    ) {
        Icon(painter = painter, contentDescription = contentDescription)
    }
}

/**
 * A modifier extension function that adds hover detection to a composable.
 *
 * This function adds a pointer input handler that detects when the pointer enters or exits
 * the composable and invokes the provided callback with the hover state.
 *
 * @param onHover Callback to invoke when the hover state changes
 * @return A modifier with hover detection
 */
fun Modifier.onHover(onHover: (Boolean) -> Unit) = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            when (event.type) {
                PointerEventType.Enter -> onHover(true)
                PointerEventType.Exit -> onHover(false)
            }
        }
    }
}