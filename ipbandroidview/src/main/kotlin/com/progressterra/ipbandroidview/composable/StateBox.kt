package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun StateBox(
    modifier: Modifier = Modifier,
    state: ScreenState,
    refresh: (() -> Unit)? = null,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier.background(IpbTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ScreenState.ERROR -> if (refresh != null) ThemedRefreshButton(onClick = refresh)
            ScreenState.LOADING -> ThemedLoadingIndicator()
            ScreenState.SUCCESS -> content()
        }
    }
}