package io.github.joshmcrose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.joshmcrose.theme.WindowTheme

@Composable
fun App() {
    Box(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(WindowTheme.colors.appBackground) // Window BG Color applied here - transparency can be applied
    ) {

    }
}