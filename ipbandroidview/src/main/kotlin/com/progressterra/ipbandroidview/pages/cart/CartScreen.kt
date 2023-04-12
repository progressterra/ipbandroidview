package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.cartitems.CartItems
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummary

@Composable
fun CartScreen(
    state: CartState, useComponent: UseCartScreen
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.cart),
                useComponent = useComponent
            )
        },
        bottomBar = {
            CartSummary(
                state = state.summary,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            CartItems(
                state = state.items,
                useComponent = useComponent
            )
        }
    }
}