package io.github.joshmcrose.window

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import io.github.joshmcrose.theme.ThemeProperties
import io.github.joshmcrose.theme.WindowTheme
import io.github.joshmcrose.theme.rememberDarkTheme
import io.github.joshmcrose.titlebar.MacTitleBar
import org.jetbrains.skiko.hostOs
import java.awt.Dimension

@Composable
fun MainWindow(
    title: String,
    onCloseRequest: () -> Unit,
    icon: Painter? = null,
    minSize: DpSize? = null,
    state: WindowState = rememberWindowState(),
    theme: ThemeProperties = ThemeProperties(),
    windowProperties: WindowProperties = WindowProperties(),
    content: @Composable () -> Unit,
) {
    val isDarkTheme = rememberDarkTheme()
    val colors by remember {
        mutableStateOf(theme.colorSchemes.darkTheme?.takeIf { isDarkTheme } ?: theme.colorSchemes.lightTheme)
    }
    val selectionColors = remember { theme.textSelectionColors }

    Window(
        onCloseRequest = onCloseRequest,
        title = title,
        state = state,
        icon = icon,
        visible = windowProperties.visible,
        undecorated = true,
        transparent = true,
        resizable = windowProperties.resizable,
        enabled = windowProperties.enabled,
        alwaysOnTop = windowProperties.alwaysOnTop,
        focusable = windowProperties.focusable,
        onPreviewKeyEvent = windowProperties.onPreviewKeyEvent,
        onKeyEvent = windowProperties.onKeyEvent
    ) {
        minSize?.let { window.minimumSize = Dimension(it.width.value.toInt(), it.height.value.toInt()) }

        // TODO: MenuBar

        WindowTheme (
            colorTheme = colors,
            ripple = theme.rippleIndication,
            textSelectionColors = selectionColors ?: LocalTextSelectionColors.current,
            shapes = theme.shapes,
            typography = theme.typography
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // TODO: TitleBar
                    if (hostOs.isMacOS){
                        MacTitleBar(

                        )
                    } else {

                    }

                    content()
                }
            }
        }
    }
}