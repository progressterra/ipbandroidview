package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedLoadingIndicator
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
data class StateBoxState(
    val state: ScreenState = ScreenState.LOADING,
    val id: String = "default"
)

sealed class StateBoxEvent {

    object Refresh : StateBoxEvent()
}

interface UseStateBox {

    fun handle(event: StateBoxEvent)
}

@Composable
fun StateBox(
    modifier: Modifier = Modifier,
    state: StateBoxState,
    useComponent: UseStateBox,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (state.state) {
            ScreenState.ERROR -> IconButton(
                modifier = modifier, onClick = {
                    useComponent.handle(state.id, StateBoxEvent.Refresh)
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