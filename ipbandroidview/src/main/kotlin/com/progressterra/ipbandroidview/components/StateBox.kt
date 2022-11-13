package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun StateBox(
    modifier: Modifier = Modifier,
    state: () -> ScreenState,
    onRefresh: () -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier.background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        when (state()) {
            ScreenState.ERROR -> ThemedRefreshButton(onClick = onRefresh)
            ScreenState.LOADING -> ThemedLoadingIndicator()
            ScreenState.SUCCESS -> content()
        }
    }
}