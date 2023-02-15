package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.CartCardComponentState

@Immutable
data class Cart(
    val listGoods: List<CartCardComponentState> = emptyList(),
    val totalPrice: SimplePrice = SimplePrice()
)