package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.CartCardState

@Immutable
data class Cart(
    val listGoods: List<CartCardState> = emptyList(),
    val totalPrice: SimplePrice = SimplePrice()
)