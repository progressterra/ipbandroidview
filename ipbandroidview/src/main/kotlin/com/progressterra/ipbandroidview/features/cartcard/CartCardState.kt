package com.progressterra.ipbandroidview.features.cartcard

import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState


data class CartCardState(
    val id: String = "",
    val name: String = "",
    val price: SimplePrice = SimplePrice(),
    val image: String = "",
    val installment: Installment = Installment(),
    val oldPrice: SimplePrice = SimplePrice(),
    val counter: CounterState = CounterState(),
    val desc: String = ""
)
