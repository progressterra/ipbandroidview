package com.progressterra.ipbandroidview.shared.ui.statebox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.ThemedLoadingIndicator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StateColumn(
    modifier: Modifier = Modifier,
    state: ScreenState,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    useComponent: UseStateBox,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val refreshState = rememberPullRefreshState(
        refreshing = state.isLoading(),
        onRefresh = { useComponent.handle(StateBoxEvent) })
    Column(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(state = refreshState, enabled = !state.isLoading()),
        verticalArrangement = if (state.isSuccess()) verticalArrangement else Arrangement.Center,
        horizontalAlignment = if (state.isSuccess()) horizontalAlignment else Alignment.CenterHorizontally
    ) {
        when (state) {
            ScreenState.ERROR -> IconButton(
                onClick = { useComponent.handle(StateBoxEvent) }
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