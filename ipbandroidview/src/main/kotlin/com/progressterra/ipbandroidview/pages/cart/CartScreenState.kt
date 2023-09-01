package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState


data class CartScreenState(
    val screen: StateColumnState = StateColumnState(),
    val summary: CartSummaryState = CartSummaryState(),
    val items: CartItemsState = CartItemsState()
)