package io.github.joshmcrose.window

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import io.github.joshmcrose.theme.ThemeProperties
import io.github.joshmcrose.theme.WindowTheme
import io.github.joshmcrose.theme.WindowTheme.colors
import io.github.joshmcrose.theme.WindowTheme.shapes
import io.github.joshmcrose.theme.rememberDarkTheme
import io.github.joshmcrose.titlebar.TitleBar
import io.github.joshmcrose.titlebar.TitleBarProperties
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
    titleBarProperties: TitleBarProperties = TitleBarProperties(),
    content: @Composable () -> Unit,
) {
    val isDarkTheme = rememberDarkTheme()
    val themeColors by remember {
        mutableStateOf(theme.colorSchemes.darkTheme?.takeIf { isDarkTheme } ?: theme.colorSchemes.lightTheme)
    }

    val defaultMinHeight by remember { mutableStateOf(titleBarProperties.height.value.toInt()) }
    var defaultMinWidth by remember { mutableStateOf(0) }

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
        window.minimumSize = minSize?.let {
            Dimension(it.width.value.toInt(), it.height.value.toInt())
        } ?: Dimension(defaultMinWidth, defaultMinHeight)

        // TODO: MenuBar

        WindowTheme(
            colorTheme = themeColors,
            ripple = theme.rippleIndication,
            shapes = theme.shapes,
            typography = theme.typography
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent,
                shape = shapes.small,
                border = BorderStroke(1.dp, colors.appBorder)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    TitleBar(
                        title = title,
                        windowState = state,
                        windowProperties = windowProperties,
                        titleBarProperties = titleBarProperties,
                        onClose = onCloseRequest,
                        onAdjustSize = if (windowProperties.resizable) { // TODO: Extract into own function
                            {
                                state.placement = if (state.placement == WindowPlacement.Floating)
                                    WindowPlacement.Fullscreen
                                else WindowPlacement.Floating
                            }
                        } else null,
                        onMaximize = if (windowProperties.resizable) { // TODO: Extract into own function
                            {
                                state.placement = if (state.placement == WindowPlacement.Maximized)
                                    WindowPlacement.Floating
                                else WindowPlacement.Maximized
                            }
                        } else null,
                        onMinimize = { state.isMinimized = !state.isMinimized },
                        windowSizeCallback = {
                            defaultMinWidth = it
                        }
                    )

                    content()
                }
            }
        }
    }
}