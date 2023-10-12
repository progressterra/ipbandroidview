package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItems
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummary
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

@Composable
fun CartScreen(
    modifier: Modifier = Modifier, state: CartScreenState, useComponent: UseCartScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.cart), useComponent = useComponent
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 36.dp)
            ) {
                CartSummary(
                    state = state.summary, useComponent = useComponent
                )
            }
        }) { _, _ ->
        StateColumn(
            modifier = Modifier.fillMaxSize(),
            state = state.screen, useComponent = useComponent
        ) {
            CartItems(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                state = state.items, useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(
        state = CartScreenState(
            screen = StateColumnState(state = ScreenState.SUCCESS),
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

@Preview
@Composable
fun CartScreenPreviewEmpty() {
    CartScreen(
        state = CartScreenState(
            screen = StateColumnState(state = ScreenState.SUCCESS),
            items = CartItemsState(
                items = emptyList(),
            ), summary = CartSummaryState(
                total = SimplePrice(0),
                proceed = ButtonState(enabled = false)
            )
        ), useComponent = UseCartScreen.Empty()
    )
}