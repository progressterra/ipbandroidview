package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.cartitems.CartItems
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummary
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

@Composable
fun CartScreen(
    state: CartState, useComponent: UseCartScreen
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.cart), useComponent = useComponent
        )
    }, bottomBar = {
        CartSummary(
            state = state.summary, useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            state = state.screenState, useComponent = useComponent
        ) {
            CartItems(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 40.dp),
                state = state.items, useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(
        state = CartState(
            screenState = ScreenState.SUCCESS,
            items = CartItemsState(
                items = listOf(
                    CartCardState(
                        id = "1",
                        name = "Товар 1",
                        price = SimplePrice(10000),
                        counter = CounterState(count = 1),
                        image = "https://picsum.photos/200/300"
                    ),
                    CartCardState(
                        id = "2",
                        name = "Товар 2",
                        price = SimplePrice(2000),
                        counter = CounterState(count = 1),
                        image = "https://picsum.photos/200/300"
                    ),
                    CartCardState(
                        id = "3",
                        name = "Товар 3",
                        price = SimplePrice(2000),
                        counter = CounterState(count = 1),
                        image = "https://picsum.photos/200/300"
                    ),
                    CartCardState(
                        id = "4",
                        name = "Товар 4",
                        price = SimplePrice(2000),
                        counter = CounterState(count = 1),
                        image = "https://picsum.photos/200/300"
                    ),
                )
            ), summary = CartSummaryState(
                total = SimplePrice(12000)
            )
        ), useComponent = UseCartScreen.Empty()
    )
}