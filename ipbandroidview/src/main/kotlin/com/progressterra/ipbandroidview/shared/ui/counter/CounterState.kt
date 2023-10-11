package com.progressterra.ipbandroidview.shared.ui.counter

import com.progressterra.ipbandroidview.shared.IsEmpty


data class CounterState(
    val id: String = "",
    val count: Int = 0
) : IsEmpty {

    override fun isEmpty(): Boolean = count == 0
}