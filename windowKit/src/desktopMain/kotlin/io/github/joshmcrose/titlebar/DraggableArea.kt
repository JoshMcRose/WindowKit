package io.github.joshmcrose.titlebar

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.WindowScope

@Composable
fun WindowScope.DraggableArea(modifier: Modifier = Modifier, adjustSize: (() -> Unit)?) = WindowDraggableArea(
    modifier = modifier
        .fillMaxWidth()
        .pointerInput(Unit) { detectTapGestures(onDoubleTap = { adjustSize?.invoke() }) }
)