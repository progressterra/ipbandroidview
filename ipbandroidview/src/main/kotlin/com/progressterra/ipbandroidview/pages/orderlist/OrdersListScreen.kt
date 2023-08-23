package com.progressterra.ipbandroidview.pages.orderlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompact
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn

@Composable
fun OrdersListScreen(
    state: OrdersListState, useComponent: UseOrdersList
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.your_orders),
            showBackButton = true,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen, useComponent = useComponent
        ) {
            val lazyItems = state.orders.collectAsLazyPagingItems()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 40.dp, start = 20.dp, end = 20.dp)
            ) {
                items(
                    count = lazyItems.itemCount,
                    key = lazyItems.itemKey { it.id }
                ) { index ->
                    lazyItems[index]?.let { OrderCompact(state = it, useComponent = useComponent) }
                }
            }
        }
    }
}