package com.progressterra.ipbandroidview.shared.ui.counter

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.IsEmpty

@Immutable
data class CounterState(
    val id: String = "",
    val count: Int = 0
) : IsEmpty {

    override fun isEmpty(): Boolean = count == 0
}