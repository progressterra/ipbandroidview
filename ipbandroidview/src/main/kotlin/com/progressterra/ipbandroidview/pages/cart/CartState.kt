package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

@Immutable
data class CartState(
    val screen: StateColumnState = StateColumnState(),
    val summary: CartSummaryState = CartSummaryState(),
    val items: CartItemsState = CartItemsState()
)