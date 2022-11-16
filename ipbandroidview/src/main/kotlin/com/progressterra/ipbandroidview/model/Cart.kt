package com.progressterra.ipbandroidview.model

data class Cart(
    val orderId: String,
    val listGoods: List<CartGoods> = emptyList(),
    val useBonuses: Boolean = false,
    val totalPrice: SimplePrice = SimplePrice(),
    val discount: SimplePrice = SimplePrice(),
    val bonuses: SimplePrice = SimplePrice(),
    val deliveryPrice: SimplePrice = SimplePrice()
)