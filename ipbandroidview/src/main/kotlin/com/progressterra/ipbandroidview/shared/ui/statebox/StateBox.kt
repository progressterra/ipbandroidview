package com.progressterra.ipbandroidview.shared.ui.statebox

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.ThemedLoadingIndicator

@Composable
fun StateBox(
    modifier: Modifier = Modifier,
    state: ScreenState,
    useComponent: UseStateBox,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            ScreenState.ERROR -> IconButton(
                modifier = Modifier.align(Alignment.Center), onClick = {
                    useComponent.handle(StateBoxEvent.Refresh)
                }
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_refresh,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
                )
            }

            ScreenState.LOADING -> ThemedLoadingIndicator(Modifier.align(Alignment.Center))
            ScreenState.SUCCESS -> content()
        }
    }
}