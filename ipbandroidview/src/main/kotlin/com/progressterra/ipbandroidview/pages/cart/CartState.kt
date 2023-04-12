package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

@Immutable
data class CartState(
    val stateBox: StateBoxState = StateBoxState(),
    val summary: CartSummaryState = CartSummaryState(),
    val items: CartItemsState = CartItemsState()
) {

    fun updateScreenState(screenState: ScreenState) = copy(
        stateBox = stateBox.updateState(screenState)
    )
}