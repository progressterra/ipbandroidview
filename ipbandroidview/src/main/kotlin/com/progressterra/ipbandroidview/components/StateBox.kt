package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.component.Screen

interface StateBoxState : Screen

@Composable
fun StateBox(
    modifier: Modifier = Modifier,
    state: StateBoxState,
    onRefresh: () -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (state.screenState) {
            ScreenState.ERROR -> ThemedRefreshButton(onClick = onRefresh)
            ScreenState.LOADING -> ThemedLoadingIndicator()
            ScreenState.SUCCESS -> content()
        }
    }
}