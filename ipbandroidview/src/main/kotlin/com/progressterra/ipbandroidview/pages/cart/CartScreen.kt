package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
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
        StateBox(
            state = state.screenState, useComponent = useComponent
        ) {
            CartItems(
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
                        loan = "по 500 ₽ 6 платежей",
                        imageUrl = "https://picsum.photos/200/300"
                    ),
                    CartCardState(
                        id = "2",
                        name = "Товар 2",
                        price = SimplePrice(2000),
                        counter = CounterState(count = 1),
                        loan = "по 500 ₽ 6 платежей",
                        imageUrl = "https://picsum.photos/200/300"
                    ),
                    CartCardState(
                        id = "3",
                        name = "Товар 3",
                        price = SimplePrice(2000),
                        counter = CounterState(count = 1),
                        loan = "по 500 ₽ 6 платежей",
                        imageUrl = "https://picsum.photos/200/300"
                    ),
                    CartCardState(
                        id = "4",
                        name = "Товар 4",
                        price = SimplePrice(2000),
                        counter = CounterState(count = 1),
                        loan = "по 500 ₽ 6 платежей",
                        imageUrl = "https://picsum.photos/200/300"
                    ),
                )
            ), summary = CartSummaryState(
                total = SimplePrice(12000)
            )
        ), useComponent = UseCartScreen.Empty()
    )
}