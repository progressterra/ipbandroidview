package com.progressterra.ipbandroidview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.composable.ThemedProgressBar
import com.progressterra.ipbandroidview.composable.ThemedRefreshButton
import com.progressterra.ipbandroidview.core.ScreenState

@Composable
fun StateBox(modifier: Modifier = Modifier, state: ScreenState, onRefresh: () -> Unit, content: @Composable (BoxScope.() -> Unit)) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (state) {
            ScreenState.ERROR -> ThemedRefreshButton(onClick = onRefresh)
            ScreenState.LOADING -> ThemedProgressBar()
            ScreenState.SUCCESS -> content()
        }
    }
}