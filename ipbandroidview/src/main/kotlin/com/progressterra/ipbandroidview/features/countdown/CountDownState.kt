package com.progressterra.ipbandroidview.features.countdown

import androidx.compose.runtime.Immutable

@Immutable
data class CountDownState(
    val count: String = "",
    val enabled: Boolean = false
) {

    fun updateCount(newCount: String): CountDownState = copy(count = newCount)

    fun updateEnabled(newEnabled: Boolean): CountDownState = copy(enabled = newEnabled)
}