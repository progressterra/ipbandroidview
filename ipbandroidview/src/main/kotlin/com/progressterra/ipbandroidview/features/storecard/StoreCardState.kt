package com.progressterra.ipbandroidview.features.storecard

import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.IsEmpty
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState


data class StoreCardState(
    val id: String = "",
    val name: String = "",
    val oldPrice: SimplePrice = SimplePrice(),
    val price: SimplePrice = SimplePrice(),
    val image: String = "",
    val installment: Installment = Installment(),
    val counter: CounterState = CounterState(),
    val properties: List<Pair<String, String>> = emptyList()
) : IsEmpty {

    override fun isEmpty(): Boolean = this == StoreCardState()
}