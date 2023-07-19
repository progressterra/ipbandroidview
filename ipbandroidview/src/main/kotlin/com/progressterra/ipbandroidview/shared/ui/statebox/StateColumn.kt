package com.progressterra.ipbandroidview.shared.ui.statebox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLoadingIndicator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StateColumn(
    modifier: Modifier = Modifier,
    state: ScreenState,
    scrollable: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    useComponent: UseStateBox,
    content: @Composable (ColumnScope.() -> Unit)
) {
    if (scrollable) {
        val refreshState = rememberPullRefreshState(
            refreshing = state.isLoading(),
            onRefresh = { useComponent.handle(StateBoxEvent) })
        Box(
            modifier = modifier
                .fillMaxSize()
                .pullRefresh(state = refreshState, enabled = !state.isLoading()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = if (state.isError()) Arrangement.Center else verticalArrangement,
                horizontalAlignment = if (state.isError()) Alignment.CenterHorizontally else horizontalAlignment
            ) {
                if (state.isSuccess()) content()
                else if (state.isError()) BrushedText(
                    text = stringResource(id = R.string.pull_for_update),
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
            }
            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = state.isLoading(),
                state = refreshState
            )
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = if (state.isError()) Arrangement.Center else verticalArrangement,
            horizontalAlignment = if (state.isError()) Alignment.CenterHorizontally else horizontalAlignment
        ) {
            when (state) {
                ScreenState.ERROR -> IconButton(
                    onClick = {
                        useComponent.handle(StateBoxEvent)
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
}