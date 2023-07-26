package com.progressterra.ipbandroidview.widgets.cartitems

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.cartcard.CartCardState

@Immutable
@optics data class CartItemsState(
    val items: List<CartCardState> = emptyList()
) { companion object }