package com.progressterra.ipbandroidview.ui.cart

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.CartBottomBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Cart

@Immutable
data class CartState(
    val screenState: ScreenState = ScreenState.LOADING,
    val cart: Cart = Cart(),
    override val userExist: Boolean = false
) : CartBottomBarState {

    override val price: String get() = cart.price
}
