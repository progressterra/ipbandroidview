package com.progressterra.ipbandroidview.features.proshkacartcard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

@Immutable
data class ProshkaCartCardState(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState(),
    val properties: List<Property> = emptyList()
) {

    sealed class Property(val name: String, val value: String) {

        class Color(name: String, value: String) : Property(name, value)

        class Size(name: String, value: String) : Property(name, value)
    }
}