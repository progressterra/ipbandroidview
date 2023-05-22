package com.progressterra.ipbandroidview.features.cartcard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

@Immutable
data class CartCardState(
    val id: String = "",
    val name: String = "",
//    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState(),
    val properties: Map<String, String> = emptyMap()
)
