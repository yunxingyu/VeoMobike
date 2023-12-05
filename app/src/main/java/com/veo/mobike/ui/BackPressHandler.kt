package com.veo.mobike.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.veo.common.extension.closeApp
import com.veo.common.extension.toast
import kotlinx.coroutines.delay

@Composable
fun BackPressHandler(isInSelectionMode: Boolean, resetSelectionMode: () -> Unit) {
    var exit by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = exit) {
        if (exit) {
            delay(2_000L)
            exit = false
        }
    }

    BackHandler(enabled = true) {
        if (isInSelectionMode) {
            resetSelectionMode()
        } else if (exit) {
            context.closeApp()
        } else {
            exit = true
            context.toast("Press again to exit")
        }
    }
}
