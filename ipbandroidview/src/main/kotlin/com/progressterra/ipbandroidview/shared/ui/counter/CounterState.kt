package com.progressterra.ipbandroidview.shared.ui.counter

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.IsEmpty

@Immutable
data class CounterState(
    val id: String = "",
    val count: Int = 0
) : IsEmpty {

    fun addOne(): CounterState = copy(count = count + 1)

    fun removeOne(): CounterState = copy(count = count - 1)

    override fun isEmpty(): Boolean = count == 0
}