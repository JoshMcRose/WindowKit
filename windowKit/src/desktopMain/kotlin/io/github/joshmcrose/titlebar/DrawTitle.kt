package io.github.joshmcrose.titlebar

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.toSize
import io.github.joshmcrose.theme.WindowTheme.colors
import io.github.joshmcrose.theme.WindowTheme.typography

internal fun Modifier.drawTitle(
    title: String,
    parentSize: IntSize,
    draw: Boolean = true,
    titleRect: (Rect) -> Unit
) = composed {
    val textMeasurer = rememberTextMeasurer()
    val style = typography.titleStyle
    val color = colors.titleBarForeground

    drawBehind {
        drawTitle(
            title = title,
            style = style,
            color = color,
            textMeasurer = textMeasurer,
            parentSize = parentSize,
            draw = draw,
            titleRect = titleRect
        )
    }
}

private fun DrawScope.drawTitle(
    title: String,
    style: TextStyle,
    color: Color,
    textMeasurer: TextMeasurer,
    parentSize: IntSize,
    draw: Boolean = true,
    titleRect: (Rect) -> Unit
) {
    val textSize = textMeasurer.measure(
        text = title,
        style = style.copy(color = color),
        softWrap = false,
        maxLines = 1
    )

    val top = parentSize.center.y - textSize.size.height.div(2f)
    val left = parentSize.center.x - textSize.size.width.div(2f)

    val offset = Offset(x = left, y = top)
    titleRect(Rect(offset = offset, size = textSize.size.toSize()))

    if (draw) drawText(
        textMeasurer = textMeasurer,
        text = title,
        style = style.copy(color = color),
        softWrap = false,
        maxLines = 1,
        topLeft = offset
    )
}