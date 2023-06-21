package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

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
        Column(
            modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp)
        ) {
            OrderDetails(
                state = state.order, useComponent = useComponent
            )
        }
    }
}