package io.github.joshmcrose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement.Floating
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import theme.AppTheme
import theme.rememberDarkTheme
import java.awt.Dimension

// TODO: CONCEPTS
//  1. Window properties
//  2. AppTheme
//  3. MenuBar
//  4. TitleBar
//  5. Window Surface

fun main() = application {
    val isDarkTheme = rememberDarkTheme()
    val windowParams = DpSize(1200.dp, 800.dp)
    val windowState = rememberWindowState(size = windowParams, placement = Floating)

    var isCloseRequested by remember { mutableStateOf(false) }

    Window(
        onCloseRequest = { isCloseRequested = true },
        title = "TITLE",
        state = windowState,
        //icon = painterResource(Res.drawable.keyboard), TODO: Keep this property in mind
        visible = true,
        undecorated = true,
        transparent = true,
        resizable = true,
        enabled = !isCloseRequested,
        alwaysOnTop = false,
        focusable = true
    ) {
        window.minimumSize = Dimension(1200.dp.value.toInt(), 800.dp.value.toInt())

        MenuBar {
            // TODO
        }

        AppTheme(isDarkTheme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    // TODO: TitleBar

                    App()
                }
            }

            if (isCloseRequested) {
                // TODO: Implement custom action before closing
                exitApplication()
            }
        }
    }
}