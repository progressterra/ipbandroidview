package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable

@Immutable
data class Cart(
    val listGoods: List<CartGoods> = emptyList(),
    val totalPrice: SimplePrice = SimplePrice()
)