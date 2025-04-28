package io.github.joshmcrose.window

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.WindowScope

@Composable
fun WindowScope.DraggableArea(
    height: Dp,
    modifier: Modifier = Modifier,
    adjustSize: () -> Unit
) = WindowDraggableArea(
    modifier = modifier
        .fillMaxWidth()
        .height(height)
        .pointerInput(Unit) { detectTapGestures(onDoubleTap = { adjustSize() }) }
)