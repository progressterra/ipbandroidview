package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCard
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox

@Composable
fun WantThisRequests(
    modifier: Modifier = Modifier,
    state: WantThisRequestsState,
    useComponent: UseWantThisRequests
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.want_this_requests),
            useComponent = useComponent,
            showBackButton = true
        )
    }) { _, _ ->
        StateBox(
            state = state.screen, useComponent = useComponent
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                items(state.items) {
                    Box(contentAlignment = Alignment.Center) {
                        WantThisCard(
                            state = it,
                            useComponent = useComponent
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WantThisRequestsPreview() {
    IpbTheme {

    }
}
