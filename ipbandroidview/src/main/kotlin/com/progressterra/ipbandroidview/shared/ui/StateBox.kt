package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedLoadingIndicator
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

sealed class StateBoxEvent {

    object Refresh : StateBoxEvent()
}

interface UseStateBox {

    fun handleEvent(id: String, event: StateBoxEvent)
}

@Composable
fun StateBox(
    modifier: Modifier = Modifier,
    id: String = "default",
    state: ScreenState,
    useComponent: UseStateBox,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ScreenState.ERROR -> IconButton(
                modifier = modifier, onClick = {
                    useComponent.handleEvent(id, StateBoxEvent.Refresh)
                }
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_refresh,
                    tint = IpbTheme.colors.iconPrimary1.asBrush()
                )
            }
            ScreenState.LOADING -> ThemedLoadingIndicator()
            ScreenState.SUCCESS -> content()
        }
    }
}