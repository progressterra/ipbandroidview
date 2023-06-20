package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox

@Composable
fun OrdersScreen(
    state: OrdersState, useComponent: UseOrders
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.your_orders),
            showBackButton = true,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateBox(
            state = state.screenState, useComponent = useComponent
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 40.dp, start = 20.dp, end = 20.dp)
            ) {
                items(state.orders) { details ->
                    OrderDetails(
                        state = details, useComponent = useComponent
                    )
                }
            }
        }
    }
}