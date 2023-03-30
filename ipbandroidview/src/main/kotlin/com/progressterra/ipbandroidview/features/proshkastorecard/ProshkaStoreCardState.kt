package com.progressterra.ipbandroidview.features.proshkastorecard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.GoodsItem
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

@Immutable
data class ProshkaStoreCardState(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState()
) {

    constructor(data: GoodsItem) : this(
        id = data.id,
        name = data.name,
        imageUrl = data.imageUrl,
        price = data.price,
        counter = data.counter
    )
}