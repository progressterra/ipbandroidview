package com.progressterra.ipbandroidview.shared.ui.statecolumn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.ThemedLoadingIndicator

@Composable
fun StateColumn(
    modifier: Modifier = Modifier,
    state: StateColumnState,
    scrollable: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    useComponent: UseStateColumn,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = modifier
            .then(
                if (scrollable) {
                    Modifier.verticalScroll(rememberScrollState())
                } else {
                    Modifier
                }
            ),
        verticalArrangement = if (state.state == ScreenState.SUCCESS) verticalArrangement else Arrangement.Center,
        horizontalAlignment = if (state.state == ScreenState.SUCCESS) horizontalAlignment else Alignment.CenterHorizontally
    ) {
        when (state.state) {
            ScreenState.ERROR -> IconButton(
                onClick = {
                    useComponent.handle(StateColumnEvent(state))
                }
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_refresh,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
                )
            }

            ScreenState.LOADING -> ThemedLoadingIndicator()
            ScreenState.SUCCESS -> content()
        }
    }
}