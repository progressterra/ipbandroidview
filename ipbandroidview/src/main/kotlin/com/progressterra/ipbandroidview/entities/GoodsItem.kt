package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

@Immutable
data class GoodsItem(
    val id: String = "",
    val name: String = "",
    val company: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val loan: String = "",
    val counter: CounterState = CounterState()
) {

    fun addOne(): GoodsItem = copy(counter = counter.addOne())

    fun removeOne(): GoodsItem = copy(counter = counter.removeOne())
}
