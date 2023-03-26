package com.progressterra.ipbandroidview.features.proshkastorecard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.CounterState

@Immutable
data class ProshkaStoreCardState(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState()
)