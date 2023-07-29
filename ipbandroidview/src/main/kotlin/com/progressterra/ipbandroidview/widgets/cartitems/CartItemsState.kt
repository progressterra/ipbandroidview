package com.progressterra.ipbandroidview.widgets.cartitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.cartcard.CartCardState

@Immutable
data class CartItemsState(
    val items: List<CartCardState> = emptyList()
)