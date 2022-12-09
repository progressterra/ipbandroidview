package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.store.Cart

data class CartState(
    val screenState: ScreenState = ScreenState.LOADING,
    val cart: Cart = Cart(),
    val userExist: Boolean = false
)