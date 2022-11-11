package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.components.StateBoxState
import com.progressterra.ipbandroidview.components.bottombar.CartBottomBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Cart

data class CartState(
    override val screenState: ScreenState = ScreenState.LOADING,
    val cart: Cart = Cart(),
    override val userExist: Boolean = false
) : CartBottomBarState, StateBoxState {

    override val price: String get() = cart.price
}
