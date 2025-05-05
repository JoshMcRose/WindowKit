package io.github.joshmcrose.titlebar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import io.github.joshmcrose.window.WindowProperties

@Composable
fun WindowScope.MacTitleBar(
    closeColor: Color,
    minColor: Color,
    maxColor: Color,
    disabledColor: Color,
    containerColor: Color,
    windowState: WindowState,
    windowProperties: WindowProperties,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: (() -> Unit)?,
    onMinimize: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().height(34.dp).background(containerColor),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MacTitleButtons(
            closeColor = closeColor,
            minColor = minColor,
            maxColor = maxColor,
            disabledColor = disabledColor,
            windowState = windowState,
            windowProperties = windowProperties,
            onClose = onClose,
            onMinimize = onMinimize,
            onAdjustSize = onAdjustSize,
            modifier = Modifier.weight(.1f)
        )

        DraggableArea(
            modifier = Modifier.fillMaxHeight().weight(.9f),
            adjustSize = onAdjustSize
        )
    }
}

@Composable
fun WindowScope.WindowsTitleBar(
    title: String,
    titleStyle: TextStyle,
    contentColor: Color,
    containerColor: Color,
    windowProperties: WindowProperties,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onAdjustSize: (() -> Unit)?,
    onMinimize: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth().height(34.dp).background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (title.length > 25) "${title.take(25)}..." else title,
            color = contentColor,
            style = titleStyle,
            modifier = Modifier.align(Alignment.Center)
        )

        Row(
            modifier = Modifier.background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DraggableArea(
                modifier = Modifier.fillMaxHeight().weight(.85f),
                adjustSize = onAdjustSize
            )

            WindowsTitleButtons(
                buttonColor = contentColor,
                windowProperties = windowProperties,
                onClose = onClose,
                onMinimize = onMinimize,
                onAdjustSize = onAdjustSize,
                modifier = Modifier.weight(.15f)
            )
        }
    }
}