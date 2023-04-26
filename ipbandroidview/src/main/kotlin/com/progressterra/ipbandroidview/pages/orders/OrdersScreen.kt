package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItems

@Composable
fun OrdersScreen(
    state: OrdersState, useComponent: UseOrders
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(id = R.string.favorites),
            useComponent = useComponent,
            showBackButton = true
        )
    }) { _, _ ->
        StateBox(
            state = state.stateBox,
            useComponent = useComponent
        ) {
            StoreItems(
                state = state.items,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    IpbTheme {}
}