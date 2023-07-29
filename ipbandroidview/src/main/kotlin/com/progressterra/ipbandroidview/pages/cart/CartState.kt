package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

@Immutable
data class CartState(
    val screenState: ScreenState = ScreenState.LOADING,
    val summary: CartSummaryState = CartSummaryState(),
    val items: CartItemsState = CartItemsState()
)