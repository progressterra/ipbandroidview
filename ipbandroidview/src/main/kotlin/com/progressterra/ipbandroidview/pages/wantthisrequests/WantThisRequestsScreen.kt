package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCard
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun WantThisRequestsScreen(
    modifier: Modifier = Modifier,
    state: WantThisRequestsScreenState,
    useComponent: UseWantThisRequestsScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.want_this_requests),
            useComponent = useComponent,
            showBackButton = true
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen, useComponent = useComponent
        ) {
            val lazyItems = state.items.collectAsLazyPagingItems()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                items(
                    count = lazyItems.itemCount,
                    key = lazyItems.itemKey { it.id }
                ) { index ->
                    lazyItems[index]?.let {
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
}