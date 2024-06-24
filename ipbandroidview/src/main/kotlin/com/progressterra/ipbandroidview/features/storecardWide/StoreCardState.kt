package com.progressterra.ipbandroidview.features.storecardwide

import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.IsEmpty
import com.progressterra.ipbandroidview.entities.Price
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

data class StoreCardWideState(
    val id: String = "",
    val name: String = "",
    val oldPrice: Price = Price(),
    val price: Price = Price(),
    val image: String = "",
    val installment: Installment = Installment(),
    val counter: CounterState = CounterState(),
    val properties: List<Pair<String, String>> = emptyList()
) : IsEmpty {

    override fun isEmpty(): Boolean = this == StoreCardWideState()
}
