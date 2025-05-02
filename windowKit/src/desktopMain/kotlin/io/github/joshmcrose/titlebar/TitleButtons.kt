package io.github.joshmcrose.titlebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import io.github.joshmcrose.windowkit.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MacTitleButtons(
    closeColor: Color,
    minColor: Color,
    maxColor: Color,
    disabledColor: Color,
    windowState: WindowState,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: () -> Unit,
    onMinimize: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val isFullScreen = remember { windowState.placement == WindowPlacement.Fullscreen }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        Spacer(Modifier.width(8.dp))
        MacButton(
            painter = Res.drawable.close,
            contentDescription = stringResource(Res.string.close),
            color = closeColor,
            isHovered = isHovered,
            onClick = onClose
        )

        MacButton(
            painter = Res.drawable.minimize,
            contentDescription = stringResource(Res.string.minimize),
            color = if (isFullScreen) disabledColor else minColor,
            isHovered = isHovered,
            onClick = onMinimize
        )

        MacButton(
            painter = if (isFullScreen) Res.drawable.open_in_full else Res.drawable.close_fullscreen,
            contentDescription =
                stringResource(if (isFullScreen) Res.string.exit_full_screen else Res.string.enter_full_Screen),
            color = maxColor,
            isHovered = isHovered,
            onClick = onAdjustSize
        )
        Spacer(Modifier.width(8.dp))
    }
}

@Composable
fun WindowsTitleButtons(
    buttonColor: Color,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: () -> Unit,
    onMinimize: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
    ) {
        Spacer(Modifier.width(8.dp))

        WindowsButton(
            buttonColor = buttonColor,
            painter = painterResource(Res.drawable.minimize),
            contentDescription = stringResource(Res.string.minimize),
            onClick = onMinimize
        )

        WindowsButton(
            buttonColor = buttonColor,
            painter = painterResource(Res.drawable.maximize),
            contentDescription = stringResource(Res.string.maximize),
            onClick = onAdjustSize
        )

        WindowsButton(
            buttonColor = buttonColor,
            painter = painterResource(Res.drawable.close),
            contentDescription = stringResource(Res.string.close),
            onClick = onClose
        )

        Spacer(Modifier.width(8.dp))
    }
}

fun Modifier.titleBarButton(color: Color, onClick: () -> Unit): Modifier = composed {
    clip(CircleShape)
        .size(14.dp)
        .background(color, shape = CircleShape) // TODO: Test this
//        .border(width = 1.dp, color = colorScheme.tertiary.copy(alpha = 0.5f), shape = CircleShape)
        .shadow(elevation = 2.dp, shape = CircleShape, clip = true)
        .clickable(onClick = onClick)
}

@Composable
fun MacButton(
    painter: DrawableResource,
    contentDescription: String?,
    color: Color,
    isHovered: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.titleBarButton(color = color, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isHovered) Icon(painter = painterResource(painter), contentDescription = contentDescription)
    }
}

@Composable
fun WindowsButton(
    buttonColor: Color,
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.size(14.dp),
        shape = RectangleShape,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            contentColor = buttonColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = buttonColor.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(4.dp),
    ) {
        Icon(painter = painter, contentDescription = contentDescription)
    }
}