package com.progressterra.ipbandroidview.widgets.cartitems

import com.progressterra.ipbandroidview.features.cartcard.CartCardState


data class CartItemsState(
    val items: List<CartCardState> = emptyList()
)