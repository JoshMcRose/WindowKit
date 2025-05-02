package io.github.joshmcrose.titlebar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.joshmcrose.theme.WindowTheme.themeColors

@Composable
fun MacTitleButtons(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: () -> Unit,
    onMinimize: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        Spacer(Modifier.width(8.dp))
        Spacer(
            modifier = Modifier.titleBarButton(
                color = themeColors["desktop_close"] ?: Color.Transparent,
                onClick = onClose
            )
        )

        Spacer(
            modifier = Modifier.titleBarButton(
                color = themeColors["desktop_minimize"] ?: Color.Transparent,
                onClick = onMinimize
            )
        )

        Spacer(
            modifier = Modifier.titleBarButton(
                color = themeColors["desktop_maximize"] ?: Color.Transparent,
                onClick = onAdjustSize
            )
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