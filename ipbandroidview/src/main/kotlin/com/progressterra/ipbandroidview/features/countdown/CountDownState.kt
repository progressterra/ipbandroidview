package com.progressterra.ipbandroidview.features.countdown

import androidx.compose.runtime.Immutable

@Immutable
data class CountDownState(
    val count: String = "",
    val enabled: Boolean = false
) {

    fun uCount(newCount: String): CountDownState = copy(count = newCount)

    fun uEnabled(newEnabled: Boolean): CountDownState = copy(enabled = newEnabled)
}